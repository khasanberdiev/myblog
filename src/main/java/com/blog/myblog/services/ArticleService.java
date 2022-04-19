package com.blog.myblog.services;
import java.util.List;

import com.blog.myblog.models.Article;
import com.blog.myblog.repositories.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    public List<Article> articleList(){
        List<Article> articles=(List<Article>) articleRepository.findAll();
        return articles;
    }

    public void deleteArticle(Long id){
        articleRepository.deleteById(id);
    }

    public void saveArticle(Article article){

        System.out.println(article.getAuthor());
        System.out.println("--------------------");
        // article.setAuthor(article.getAuthor());
        articleRepository.save(article);
    }


    
}
