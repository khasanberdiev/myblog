package com.blog.myblog.services;
import java.util.List;
import java.util.Optional;


import com.blog.myblog.exceptions.NotFoundException;
import com.blog.myblog.models.Article;
import com.blog.myblog.repositories.ArticleRepository;
import com.blog.myblog.repositories.ArticleSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleSpecification articleSpecification;


    // public Page<Article> articlePageableList(int pageNumber, int pageSize, String sortField, String sortDirection){
    //     Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
    //         Sort.by(sortField).descending();
    //     Pageable pageable  = PageRequest.of(pageNumber - 1, pageSize, sort);
    //     return articleRepository.findAll(pageable);
    // }

    public Page<Article> articleSearchPageableList(int pageNumber, int pageSize, String sortField, String sortDirection, 
                                                    String searchQuery){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
            Sort.by(sortField).descending();
        Pageable pageable  = PageRequest.of(pageNumber - 1, pageSize, sort);
        Specification<Article> spec=articleSpecification.getArticles(searchQuery);
        articleRepository.findAll(spec, pageable);
        return articleRepository.findAll(spec, pageable);
    }
    public List<Article> articleList(){
        return (List<Article>) articleRepository.findAll();
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
