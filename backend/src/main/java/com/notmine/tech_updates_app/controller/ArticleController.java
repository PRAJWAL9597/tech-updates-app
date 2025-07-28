package com.notmine.tech_updates_app.controller;

import com.notmine.tech_updates_app.model.Article;
import com.notmine.tech_updates_app.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ArticleController {

    private final ArticleService articleService;

   public  ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/api/articles")
    public List<Article> getArticles() {
     return articleService.fetchLatestArticles();
    }
}
