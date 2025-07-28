package com.notmine.tech_updates_app.service;


import org.springframework.stereotype.Service;
import com.notmine.tech_updates_app.model.Article;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleService{
            private static final String API_KEY = "5414cbd9861b42369367179c88af6c0a";
            private static final String NEWS_API_URL = "https://newsapi.org/v2/top-headlines?category=technology&language=en&apiKey=" + API_KEY;

             public List<Article> fetchLatestArticles(){
                    RestTemplate restTemplate = new RestTemplate();
                    String jsonResponse = restTemplate.getForObject(NEWS_API_URL, String.class);
                    List<Article> articles = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONArray articlesArray = jsonObject.getJSONArray("articles");

                    for (int i = 0; i< articlesArray.length(); i++){
                        JSONObject obj = articlesArray.getJSONObject(i);
                        Article article = new Article();
                        article.setTitle(obj.getString("title"));
                        article.setUrl(obj.getString("url"));
                        article.setContent(obj.optString("content",""));
                        article.setPublishedAt(obj.optString("Published At",""));

                        article.setSource(obj.getJSONObject("source").getString("name"));
                        article.setCategory("technology");
                        articles.add(article);
                    }
                   return articles;
            }
}
