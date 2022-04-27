package com.blog.myblog.repositories;

import com.blog.myblog.models.Author;


import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRespository extends PagingAndSortingRepository<Author, Long> {

    
    
}
