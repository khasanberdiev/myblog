package com.blog.myblog.repositories;


import com.blog.myblog.models.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAll(Specification<Article> spec, Pageable pageable);
    Page<Article>findByTitleOrBody(String title, String body, Pageable pageable);

    @Query(value = "SELECT * FROM article WHERE ((title=:searchQuery or :searchQuery is null or :searchQuery = '')  and status=:status and (category_id=:category or :category is null or :category = ''))",  nativeQuery = true)
    public Page<Article> findByTitleAndStatus(String searchQuery, String status, Integer category, Pageable pageable);
   
    @Query(value = "SELECT * FROM article WHERE ((title=:title or :title is null or :title = '') or (body=:body or :body is null or :body = '')) and (status=:status or :status is null or :status = '')",  nativeQuery = true)
    public Page<Article> findByTitleAndBodyAndStatus(@Param("title") String title, @Param("body") String body, @Param("status") String status, Pageable pageable);
    
    @Query(value = "SELECT * FROM article WHERE (title=:title or :title is null or :title = '') and (status=:status or :status is null or :status = '')",  nativeQuery = true)
    public Page<Article> findByTitlesAndStatus(@Param("title") String title,  @Param("status") String status, Pageable pageable);

    @Query(value = "SELECT * FROM article WHERE (body=:body or :body is null or :body = '') and (status=:status or :status is null or :status = '')",  nativeQuery = true)
    public Page<Article> findByBodyAndStatus(@Param("body") String body, @Param("status") String status, Pageable pageable);
    
    


    
}
