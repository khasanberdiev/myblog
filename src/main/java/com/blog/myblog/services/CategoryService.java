package com.blog.myblog.services;

import com.blog.myblog.repositories.CategoryRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import com.blog.myblog.exceptions.NotFoundException;
import com.blog.myblog.models.Category;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository categoryRespository;
    
    public Page<Category> categoryPageableList(int pageNumber, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
            Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return categoryRespository.findAll(pageable);
    }

    public List<Category> categoryList(){
        return (List<Category>) categoryRespository.findAll();
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
