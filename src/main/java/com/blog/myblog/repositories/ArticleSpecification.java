package com.blog.myblog.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import com.blog.myblog.models.Article;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component


public class ArticleSpecification {
    public Specification<Article> getArticles(String searchQuery, String searchFilter, String searchStatus) {
          return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // Predicate searchByTitle=criteriaBuilder.like(root.get("title"), "%" + searchQuery+ "%");
            // Predicate searchByBody=criteriaBuilder.like(root.get("body"), "%" + searchQuery+ "%");
            
            // Predicate searchByTitleBody=criteriaBuilder.or(searchByTitle, searchByBody);
            // Predicate searchByStatus=(!searchStatus.isEmpty() && searchStatus!=null )?criteriaBuilder.like(root.get("status"),"%" + searchStatus+ "%"):null;

            // Predicate searchByStatus=null;
            // if (searchFilter.equals("title")) {
            //     predicates.add(criteriaBuilder.like(root.get("title"), "%" + searchQuery+ "%"));

            //     // predicates.add(searchByStatus != null ?searchByTitle: criteriaBuilder.and(searchByTitle, searchByStatus));
            // }
            // if (searchFilter.equals("body")) {
            //     // predicates.add(searchByStatus != null? searchByBody: criteriaBuilder.and(searchByBody, searchByStatus));
            //     predicates.add(criteriaBuilder.like(root.get("body"), "%" + searchQuery+ "%"));
            // }
            
        //    if (searchFilter.equals("all")){
                // predicates.add(criteriaBuilder.like(root.get("title"), "%" + searchQuery+ "%"));
                // predicates.add(criteriaBuilder.like(root.get("body"), "%" + searchQuery+ "%"));
            // }
          
            // if(searchFilter.equals("") &&  searchFilter.isEmpty()){
            //     predicates.add(criteriaBuilder.like(root.get("title"), "%" + searchQuery+ "%"));
            //     predicates.add(criteriaBuilder.like(root.get("body"), "%" + searchQuery+ "%"));
            // }
            
            
                      
            // if (searchFilter!=null && !searchFilter.isEmpty() && searchFilter.equals("title")) {
            //     predicates.add(criteriaBuilder.like(root.get("title"), "%" + searchQuery+ "%"));
            // }
            // if (searchFilter!=null && !searchFilter.isEmpty() &&  searchFilter.equals("body")){
            //     predicates.add(criteriaBuilder.like(root.get("body"),"%" + searchQuery + "%"));
               
            // }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
           
        };
    }}
        




// public class ArticleSpecification {
//     public Specification<Article> getArticles(String searchQuery, String searchFilter, String searchStatus) {
//         Specification<Article> temp=null;
//         Specification<Article> spec=null;

//         if(searchQuery != null && !searchQuery.isEmpty() && searchFilter.isEmpty()){
//             System.out.println("inside all");
//             temp=getArticleBySearchQuery("searchQuery",searchQuery);
//             spec=spec!=null?Specification.where(spec).and(temp):temp;
//         }
//       System.out.println(searchFilter);
//         if(searchFilter!=null && !searchFilter.isEmpty() && searchFilter.equals("title")){
//             System.out.println("inside title");
//             temp=getArticleBySearchByTitle("searchQuery",searchQuery);
// 			spec=spec!=null?Specification.where(spec).and(temp):temp;
//         }

//         if(searchFilter!=null && !searchFilter.isEmpty() && searchFilter.equals("body")){
//             System.out.println("inside body");
//             temp=getArticleBySearchByBody("searchQuery",searchQuery);
// 			spec=spec!=null?Specification.where(spec).and(temp):temp;
//         }

//         // if(searchStatus != null && !searchStatus.isEmpty()){
//         //     temp=getArticleBySearchStatus("searchStatus",searchStatus);
// 		// 	spec=spec!=null?Specification.where(spec).and(temp):temp;
//         // }

//         return spec;
//     }

//     private Specification<Article> getArticleBySearchByTitle(String string, String searchQuery) {
//         return (root, query, criteriaBuilder) -> {
//             return criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" +searchQuery.toString().toLowerCase() +"%");
//         };
//     }

//     private Specification<Article> getArticleBySearchByBody(String string, String searchQuery) {
//         return (root, query, criteriaBuilder) -> {
//             return criteriaBuilder.like(criteriaBuilder.lower(root.get("body")), "%" +searchQuery.toString().toLowerCase() +"%");
//         };
//     }

//     // private Specification<Article> getArticleBySearchStatus(String string, String searchStatus) {
//     //     return (root, query, criteriaBuilder) -> {
//     //         return criteriaBuilder.equal(root.get("status"), searchStatus);
//     //     };
//     // }

//     private Specification<Article> getArticleBySearchQuery(String string, String searchQuery) {
//         return (root, query, criteriaBuilder) -> {
//             List<Predicate> predicates = new ArrayList<>();
            
            
//             if (searchQuery != null && !searchQuery.isEmpty()) {
//                 predicates.add(criteriaBuilder.like(root.get("body"), "%" + searchQuery+ "%"));
//             }
//             if (searchQuery != null && !searchQuery.isEmpty()) {
//                 predicates.add(criteriaBuilder.like(root.get("title"),"%" + searchQuery + "%"));
//             }
//             return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//         };
//     }
// }
