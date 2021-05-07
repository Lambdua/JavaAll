package com.lt.module;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liangtao
 * @description 文章module
 * @date 2021年05月06 16:33
 **/
@Builder
@AllArgsConstructor
@Data
public class Article implements Serializable {
    private static List<Article> innerArticle;

    private static List<Article> oldArticle = new ArrayList<>();

    String id;

    /**
     * 标题
     */
    String title;

    /**
     * 文章链接
     */
    String link;

    /**
     * 发布者
     */
    User poster;

    /**
     * 文章时间戳
     */
    Long timeStamp;

    /**
     * 点赞数
     */
    Integer votes;

    public static Article buildArticle() {
        return listArticle(1, true).get(0);
    }

    public static List<Article> listArticle(int total, boolean isRenew) {
        if (!isRenew && CollUtil.isNotEmpty(innerArticle)) {
            return innerArticle;
        }
        initial(total);
        return innerArticle;
    }

    private static String baseStr = "靓啊海关监管法规叫老公后i额外经" +
            "琴女能吃饱女和购房价款" +
            "纪人就发哦分就哦啊几个号和公检法日哦二五九附件二欧文居然敢认为日军侵华交给屁股后厄普钢结构我去会如何Greg" +
            "七二根本不敢去好根据其如果人会回去改排气如果将回顾回顾黄金白银还没下课十八个人好恶";

    private static void initial(int total) {
        if (CollUtil.isNotEmpty(innerArticle)) {
            oldArticle.addAll(innerArticle);
        }
        innerArticle = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            Article article = Article.builder()
                    .id(RandomUtil.randomNumbers(12))
                    .link(RandomUtil.randomString(20))
                    .poster(new User(RandomUtil.randomNumbers(12)))
                    .timeStamp(System.currentTimeMillis())
                    .votes(0)
                    .title(RandomUtil.randomString(baseStr, RandomUtil.randomInt(3, 10)))
                    .build();
            innerArticle.add(article);
        }
    }
}
