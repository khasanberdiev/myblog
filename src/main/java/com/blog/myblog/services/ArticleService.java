package com.blog.myblog.services;
import java.util.List;
import java.util.Optional;

import com.blog.myblog.exceptions.NotFoundException;
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

    public Article findById(Long id){
        Optional<Article> findArticleById=articleRepository.findById(id);
        if (!findArticleById.isPresent()){
            throw new NotFoundException("Not Found");
        }
        
        return findArticleById.get();
    }

    public void deleteArticle(Long id){
        articleRepository.deleteById(id);
    }

    public void saveArticle(Article article){

        articleRepository.save(article);
    }


    
}
