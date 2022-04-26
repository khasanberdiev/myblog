package com.blog.myblog.repositories;
import com.blog.myblog.models.Category;


import org.springframework.data.repository.PagingAndSortingRepository;
public interface CategoryRespository extends PagingAndSortingRepository<Category, Long> {
    
}
