package com.blog.myblog.repositories;


import com.blog.myblog.models.Article;
import com.blog.myblog.models.custom.ArticleArchive;
// import com.blog.myblog.models.CategoryCount;
import com.blog.myblog.models.custom.CategoryCountInterface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
// import antlr.collections.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findAll(Specification<Article> spec, Pageable pageable);
    Page<Article>findByTitleOrBody(String title, String body, Pageable pageable);

    @Query(value="select count(article.category_id) as categoryCount, category.Category_Name as categoryName, "+
                "article.category_id as categoryId from article "+
                "inner join category on article.category_id=category.id "+
                "group by article.category_id",  nativeQuery = true)
    public List<CategoryCountInterface> getCategoryAndCount();

    @Query(value="select created as getMonth, count(id) as articleMonthlyCount from article GROUP BY MONTH(created)",  nativeQuery = true)
    public List<ArticleArchive> getArticleArchive();

    @Query(value = "SELECT * FROM article WHERE ((title=:searchQuery or :searchQuery is null or :searchQuery = '')  and status=:status and (category_id=:category or :category is null or :category = ''))",  nativeQuery = true)
    public Page<Article> findByTitleAndStatus(String searchQuery, String status, Integer category, Pageable pageable);
   
    @Query(value = "SELECT * FROM article WHERE ((title=:title or :title is null or :title = '') or (body=:body or :body is null or :body = '')) and (status=:status or :status is null or :status = '')",  nativeQuery = true)
    public Page<Article> findByTitleAndBodyAndStatus(@Param("title") String title, @Param("body") String body, @Param("status") String status, Pageable pageable);
    
    @Query(value = "SELECT * FROM article WHERE (title=:title or :title is null or :title = '') and (status=:status or :status is null or :status = '')",  nativeQuery = true)
    public Page<Article> findByTitlesAndStatus(@Param("title") String title,  @Param("status") String status, Pageable pageable);

    @Query(value = "SELECT * FROM article WHERE (body=:body or :body is null or :body = '') and (status=:status or :status is null or :status = '')",  nativeQuery = true)
    public Page<Article> findByBodyAndStatus(@Param("body") String body, @Param("status") String status, Pageable pageable);
    
    


    
}
