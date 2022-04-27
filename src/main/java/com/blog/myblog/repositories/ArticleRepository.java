package com.blog.myblog.repositories;

import com.blog.myblog.models.Article;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
    
}
