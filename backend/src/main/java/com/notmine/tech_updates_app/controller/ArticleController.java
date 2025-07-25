package com.notmine.tech_updates_app.controller;

import com.notmine.tech_updates_app.model.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ArticleController {

    @GetMapping("/api/articles")
    public List<Article> getArticles() {
        Article example = new Article();
        example.setId(1L);
        example.setTitle("Example Article: AI in 2025");
        example.setUrl("https://example.com/ai-2025");
        example.setContent("This is a sample article about AI trends in 2025.");
        example.setPublishedAt("2025-07-25");
        example.setSource("TechCrunch");
        example.setCategory("AI");

        List<Article> article = new ArrayList<>();
        article.add(example);

        return article;
    }
}
