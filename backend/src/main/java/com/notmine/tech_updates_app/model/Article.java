package com.notmine.tech_updates_app.model;

public class Article {
        private long id;
        private String title;
        private String content;
        private String publishedAt;
        private String source;
        private String category;
        private String url;

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
