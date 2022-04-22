package com.blog.myblog.services;

import com.blog.myblog.repositories.CategoryRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.blog.myblog.exceptions.NotFoundException;
import com.blog.myblog.models.Category;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository categoryRespository;
    
    public List<Category> categoryList(){
        List<Category> categories=(List<Category>) categoryRespository.findAll();
        return categories; 
    }

    

    public void deleteCategoryById(Long id){
        categoryRespository.deleteById(id);
    }

    public void saveCategory(Category category){
        categoryRespository.save(category);
    }
    public Category findById(long id){
        Optional<Category> categoryFindById=categoryRespository.findById(id);
        if(!categoryFindById.isPresent()){
            throw new NotFoundException("Category Not Found");
        }
        return categoryFindById.get();

    }

    
}
