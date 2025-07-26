package com.notmine.tech_updates_app.model;

import jakarta.persistence.*;

@Entity //mark class as database entity
@Table(name = "articles")

public class Article {

        @Id //marks this field as primary key
        @GeneratedValue(strategy = GenerationType.IDENTITY) // tells database to autogenerate id

        private long id;
        private String title;
         private String url;

        @Column(columnDefinition = "TEXT") //use text type for potential long content
        private String content;
        private String publishedAt;
        private String source;
        private String category;
       
        

        public Article(){}
        public Article(long id,String title,String content,String publishedAt,String source,String category,String url){
                this.id = id;
                this.title = title;
                this.content = content;
                this.publishedAt = publishedAt;
                this.source = source;
                this.category = category;
                this.url = url;

        } 

        //getters and setters 

        public long getId(){return id;}
        public void setId(long id){this.id = id;}

        public String getTitle(){return title;}
        public void setTitle(String title){this.title = title;}

        public String getContent(){return content;}
        public void setContent(String content){this.content=content;}

        public String getPublishedAt(){return publishedAt;}
        public void setPublishedAt(String publishedAt){this.publishedAt = publishedAt;}
        
        public String getSource(){return source;}
        public void setSource(String source){this.source = source;}

        public String getCategory(){return category;}
        public void setCategory(String category){this.category = category;}
        
        public String getUrl(){return url;}
        public void setUrl(String url){this.url = url;}

   

        
}
