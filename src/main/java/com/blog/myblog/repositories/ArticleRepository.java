package com.blog.myblog.repositories;

import com.blog.myblog.models.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

    // Page<Article> search(String title, String body, Pageable pageable);
    Page<Article> findAll(Specification<Article> spec, org.springframework.data.domain.Pageable pageable);

    
}
