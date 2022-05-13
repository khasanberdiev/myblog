package com.blog.myblog.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.blog.myblog.models.Article;

import org.springframework.stereotype.Component;
@Component
public class ArticleCriteriaRepository {
    private EntityManager entityManager;
    
    public List<Article> findByArticleSearchCriteria(String searchQuery){
        CriteriaBuilder  criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);      
        Root<Article> articleRoot = criteriaQuery.from(Article.class);
        criteriaQuery.select(articleRoot);
        Predicate predicate = criteriaBuilder.notEqual(articleRoot.get("title"), searchQuery);
        criteriaQuery.where(predicate);
        TypedQuery<Article> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Article> articleList = typedQuery.getResultList();
        return articleList;
    }
}
