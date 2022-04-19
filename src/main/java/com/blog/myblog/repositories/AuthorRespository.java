package com.blog.myblog.repositories;

import com.blog.myblog.models.Author;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRespository extends CrudRepository<Author, Long> {
    
}
