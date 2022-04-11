package com.blog.myblog.repositories;

import org.springframework.data.repository.CrudRepository;
import com.blog.myblog.models.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    
}
