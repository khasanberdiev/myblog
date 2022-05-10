package com.blog.myblog.repositories;






import com.blog.myblog.models.Category;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRespository extends PagingAndSortingRepository<Category, Long> {

    // @Query(value="SELECT m FROM category m WHERE m.categoryName LIKE  %?1%",  nativeQuery =true)
    // @Query(value="SELECT c FROM category c  WHERE  categoryName=:searchQuery",  nativeQuery = true)
    // @Query(value = "SELECT t FROM category t WHERE t.categoryName LIKE %?1%", nativeQuery = true)
    public Page<Category> findByCategoryName(String searchQuery, Pageable pageable);
    public Page<Category> findByCategoryDescription(String searchQuery, Pageable pageable);



    // public Page<Category> findByDescription(String description,  Pageable pageable);
    // public Page<Category> findByStatus(Status status, Pageable pageable);  
    // public List<Category> findByCategoryName(String categoryName);
    // public List<Category> findByCategoryDescription(String description);
    // public List<Category> findByCategoryStatus(String categoryStatus);
    // @Query("select * from category where categoryName=:categoryName or description=:description and status=:status ")
    // Page<Category> findByCategoryNameOrDescriptionAndStatus(String categoryName, String description, String status, Pageable pageable);
    
}
