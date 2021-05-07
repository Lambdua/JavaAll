package com.lt.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.lt.module.Article;
import com.lt.utils.RedisClientFactory;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liangtao
 * @description 文章操作服务层
 * @date 2021年05月06 16:38
 **/
public class ArticleService {

    /**
     * 存放文章列表的有序集合key，根据时间排序
     */
    public static final String ARTICLE_ORDER_BY_PUB_DATE_KEY = "article-time-order";

    /**
     * 存放文章列表的有序集合key,根据得分排序
     */
    public static final String ARTICLE_ORDER_BY_SCORE_KEY = "article-score-order";

    /**
     * 有序集合member的前缀， 完整member是 article:id
     */
    private static final String ARTICLE_MEMBER_PREFIX = "article:";

    /**
     * 文章集合
     */
    private static final String ARTICLE_INFO = "article:info:";

    /**
     * 记录每篇文章已经投票用户信息集合
     */
    private static final String USER_VOTED_PREFIX_KEY = "user-voted:";


    private static RedisCommands<String, String> command = RedisClientFactory.buildCommand();

    /**
     * 文章投票
     *
     * @author liangtao
     * @date 2021/5/6
     **/
    public void voteArticle(String userId, String articleId) {
        String member = ARTICLE_MEMBER_PREFIX + articleId;
        //预校验
        String articleJson = command.get(ARTICLE_INFO+articleId);
        if (StrUtil.isNullOrUndefined(articleJson)) {
            System.out.println("没有此文章： " + articleId);
            return;
        }
        //1. 使用ZSCORE命令来检查文章时间排序集合中的此篇文章是否发布时间没有超过一天，如果超过了，就不能进行投票了。
        Double articleDateScore = command.zscore(ARTICLE_ORDER_BY_PUB_DATE_KEY, member);
        LocalDateTime articlePubTime = LocalDateTime.ofEpochSecond(articleDateScore.longValue() / 1000, 0, ZoneOffset.ofHours(8));
        if (LocalDateTime.now().isAfter(articlePubTime.plusDays(1))) {
            System.out.print("文章" + articleId + "发布时间： " + articlePubTime.toString());
            System.out.println("已经超过一天时间，不再支持投票");
            return;
        }
        //2. 使用SADD尝试将用户id插入到文章的已投票集合中，插入失败则说明改用户以前对该文章投过票，不能继续投票。
        Long result = command.sadd(USER_VOTED_PREFIX_KEY + articleId, "user:" + userId);
        if (result == 0L) {
            System.out.println("用户： " + userId + "已投过票，不能再次投票");
            return;
        }
        //3. 使用 ZINCRBY 命令为文章的评分添加432分，ZINCRBY命令将有序集合的成员分值进行自增操作
        /**
         * 计算评分时与支持票数量相乘的常量为432，
         * 这个常量是通过将一天的秒数(86 400)除以文章展示一天所需的支持票数量（200）得出的:
         * 文章每获得一张支持票,程序就需要将文章的评分增加432分。
         */
        command.zincrby(ARTICLE_ORDER_BY_SCORE_KEY, 432, member);
        //将票数加1,这里我们是序列化后存储到redis，而不是每一个对象单独存储在散列表中，只有拿出来进行对象的 反序列化后操作完在存储到redis中
        //如果我们是直接存储的散列对象键值对 可以使用 command.hincrby() 直接将vote字段+1
        Article curArticle = JSONUtil.toBean(articleJson, Article.class);
        curArticle.setVotes(curArticle.getVotes() + 1);
        command.set(ARTICLE_INFO+articleId, JSONUtil.toJsonStr(curArticle));
    }

    public void addArticle(Article article) {
        System.out.println("新增文章： "+article);
        String jsonArticle = JSONUtil.toJsonStr(article);
        String member = ARTICLE_MEMBER_PREFIX + article.getId();
        //将文章存储到redis中
        command.set(ARTICLE_INFO+article.getId(),jsonArticle);
        //将article放入到按照时间排序中
        command.zadd(ARTICLE_ORDER_BY_PUB_DATE_KEY, article.getTimeStamp(), member);
        //将article放入到得分排序中
        command.zadd(ARTICLE_ORDER_BY_SCORE_KEY, article.getTimeStamp(), member);
    }

    /**
     * 获取文章集合
     *
     * @param typeKey  类型
     *                 根据时间排序{@value ARTICLE_ORDER_BY_PUB_DATE_KEY}
     *                 还是得分排序 {@value ARTICLE_ORDER_BY_SCORE_KEY}
     * @param pageSize 页量
     * @param page     页码
     * @return java.util.List<com.lt.module.Article>
     * @author liangtao
     * @date 2021/5/7
     **/
    public List<Article> showList(String typeKey, int pageSize, int page) {
        int start = (page - 1) * pageSize;
        int end = start + pageSize;
        //有序集合默认时从小到大排序，我们需要的是从大到小，使用zrevrange
        List<String> articleIdList = command.zrevrange(typeKey, start, end);
        return articleIdList.stream()
                .map(item -> command.hget(ARTICLE_INFO, item))
                .map(artJson -> JSONUtil.toBean(artJson, Article.class))
                .collect(Collectors.toList());
    }

}
