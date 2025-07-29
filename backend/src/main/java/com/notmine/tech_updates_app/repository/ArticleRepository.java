package com.notmine.tech_updates_app.repository;

import com.notmine.tech_updates_app.model.Article;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

    Optional<Article> findByUrl(String url);
}