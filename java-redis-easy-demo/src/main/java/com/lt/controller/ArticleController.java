package com.lt.controller;

import com.lt.module.Article;
import com.lt.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liangtao
 * @description
 * @date 2021年05月07 10:27
 **/
@RestController
@RequestMapping("/article")
public class ArticleController {
    private ArticleService articleService = new ArticleService();

    @PostMapping("/addArticle")
    public List<Article> addArticle(@RequestParam Integer total) {
        List<Article> articles = Article.listArticle(total, true);
        articles.forEach(articleService::addArticle);
        return articles;
    }

    @GetMapping("/listArticle")
    public List<Article> listArticle(@RequestParam("orderType") String orderType
            , @RequestParam("page") Integer page) {
        List<Article> articles = articleService.showList(orderType, 10, page);
        return articles;
    }

    @PutMapping("/voteArticle")
    public void  voteArticle(@RequestParam("userId")String userId,@RequestParam("articleId")String articleId){
        articleService.voteArticle(userId,articleId);
    }
}
