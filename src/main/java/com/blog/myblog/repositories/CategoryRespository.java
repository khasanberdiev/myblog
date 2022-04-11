package com.blog.myblog.repositories;
import com.blog.myblog.models.Category;

import org.springframework.data.repository.CrudRepository;
public interface CategoryRespository extends CrudRepository<Category, Long> {
    
}
