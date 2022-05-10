package com.blog.myblog.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import com.blog.myblog.models.Article;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ArticleSpecification {
    public Specification<Article> getArticles(String searchQuery) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (searchQuery != null && !searchQuery.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("body")),
                        "%" + searchQuery.toLowerCase() + "%"));
            }
            if (searchQuery != null && !searchQuery.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),
                        "%" + searchQuery.toLowerCase() + "%"));
            }

            // if (searchQuery != null && !searchQuery.isEmpty()) {
            //     predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),
            //             "%" + searchQuery + "%"));
            // }
            // cr.select(root).where(cb.like(root.get("itemName"), "%chair%"));

            // System.out.println("-------------------------");
            // System.out.println(predicates.toString());
           
            // query.orderBy(criteriaBuilder.desc(root.get("experience")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}