package com.notmine.tech_updates_app.service;

import com.notmine.tech_updates_app.model.Article;
import com.notmine.tech_updates_app.repository.ArticleRepository; // Import the new repository
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ArticleService {
    
    private static final String API_KEY = "5414cbd9861b42369367179c88af6c0a";
    private static final String NEWS_API_URL = "https://newsapi.org/v2/top-headlines?category=technology&language=en&apiKey=" + API_KEY;

    @Autowired 
    private ArticleRepository articleRepository;

    public List<Article> fetchAndSaveArticles() {
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(NEWS_API_URL, String.class);

        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray articlesArray = jsonObject.getJSONArray("articles");

        for (int i = 0; i < articlesArray.length(); i++) {
            JSONObject obj = articlesArray.getJSONObject(i);
            String articleUrl = obj.getString("url");

            
            if (articleRepository.findByUrl(articleUrl).isEmpty()) {
                
                Article newArticle = new Article();
                newArticle.setTitle(obj.getString("title"));
                newArticle.setUrl(articleUrl);
                newArticle.setContent(obj.optString("content", ""));
                newArticle.setPublishedAt(obj.optString("publishedAt", ""));
                newArticle.setSource(obj.getJSONObject("source").getString("name"));
                newArticle.setCategory("technology");

                articleRepository.save(newArticle); 
                System.out.println("Saved new article: " + newArticle.getTitle());
            }
        }

        
        return articleRepository.findAll();
    }
}
