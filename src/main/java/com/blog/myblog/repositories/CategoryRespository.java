package com.blog.myblog.repositories;






import java.util.List;

import com.blog.myblog.models.Category;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;


public interface CategoryRespository extends JpaRepository<Category, Long> {

    @Override
    List<Category> findAll();
    // @Query(value="SELECT m FROM category m WHERE m.categoryName LIKE  %?1%",  nativeQuery =true)
    // @Query(value="SELECT c FROM category c  WHERE  categoryName=:searchQuery",  nativeQuery = true)
    // @Query(value = "SELECT t FROM category t WHERE t.categoryName LIKE %?1%", nativeQuery = true)
    public Page<Category> findByCategoryName(String searchQuery, Pageable pageable);
    public Page<Category> findByCategoryDescription(String searchQuery, Pageable pageable);
    // @Query(value="select count(i) from category  i where (i.id=:category_id or :category_id is null or :category_id = '') ", nativeQuery =true)
    // public Integer countById(Long category_id);



    // public Page<Category> findByDescription(String description,  Pageable pageable);
    // public Page<Category> findByStatus(Status status, Pageable pageable);  
    // public List<Category> findByCategoryName(String categoryName);
    // public List<Category> findByCategoryDescription(String description);
    // public List<Category> findByCategoryStatus(String categoryStatus);
    // @Query("select * from category where categoryName=:categoryName or description=:description and status=:status ")
    // Page<Category> findByCategoryNameOrDescriptionAndStatus(String categoryName, String description, String status, Pageable pageable);
    
}
