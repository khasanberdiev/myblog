package com.blog.myblog.services;
import java.util.List;
import java.util.Optional;


import com.blog.myblog.exceptions.NotFoundException;
import com.blog.myblog.models.Article;
import com.blog.myblog.models.custom.ArticleArchive;
import com.blog.myblog.models.custom.CategoryCountInterface;
// import com.blog.myblog.models.CategoryCount;
import com.blog.myblog.repositories.ArticleRepository;
// import com.blog.myblog.repositories.ArticleSpecification;
// import com.blog.myblog.repositories.ArticleSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
// import org.springframework.data.jpa.domain.Specification;
// import org.springframework.data.jpa.domain.Specification;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired 
    private AuthorService authorService;

    private final static String SEARCH_STATUS="active";
    private final static String SORT_FIELD="id";


    // public Page<Article> articlePageableList(int pageNumber, int pageSize, String sortField, String sortDirection){
    //     Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
    //         Sort.by(sortField).descending();
    //     Pageable pageable  = PageRequest.of(pageNumber - 1, pageSize, sort);
    //     return articleRepository.findAll(pageable);
    // }

    

    public Page<Article> articleSearchPageableList(int pageNumber, 
                                                    int pageSize, 
                                                    String sortField, 
                                                    String sortDirection, 
                                                    String searchQuery,
                                                    String searchFilter,
                                                    String searchStatus){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
            Sort.by(sortField).descending();
        Pageable pageable  = PageRequest.of(pageNumber - 1, pageSize, sort);
        // Specification<Article> spec=articleSpecification.getArticles(searchQuery, searchFilter, searchStatus);
        
        String title="";
        String body ="";
        // if(!searchFilter==null && !searchFilter.isEmpty()){

        // }
        if(searchFilter!=null && !searchFilter.isEmpty() && searchFilter.equals("title")){
            title=searchQuery;
            // return articleRepository.findByTitleAndStatus(searchQuery, searchStatus, pageable );
        }
        if(searchFilter!=null && !searchFilter.isEmpty() && searchFilter.equals("body")){
            body=searchQuery;
            // return articleRepository.findByBodyAndStatus(searchQuery, searchStatus, pageable );
        }
        return articleRepository.findByTitleAndBodyAndStatus(title, body, searchStatus, pageable );
       

    }
    public List<Article> articleList(){
        return (List<Article>) articleRepository.findAll();
    }

    public Page<Article> activeArticleList( int pageNumber, 
                                            int pageSize, 
                                            String searchQuery, 
                                            Integer filterByCategory){

        Sort sort = Sort.by(SORT_FIELD).descending();
        Pageable pageable  = PageRequest.of(pageNumber - 1, pageSize, sort);

        return (Page<Article>) articleRepository.findByTitleAndStatus(searchQuery, SEARCH_STATUS, filterByCategory, pageable);
    }

    public List<Article> getTopArticles(){
        List<Article> articles=articleRepository.findAll();
        return articles;
    }

    public List<CategoryCountInterface> getCategoryAndCount(){
        return (List<CategoryCountInterface>) articleRepository.getCategoryAndCount();
    }

    public List<ArticleArchive> articleArchive(){
        return (List<ArticleArchive>) articleRepository.getArticleArchive();
    }

    public Article findById(Long id){
        Optional<Article> findArticleById=articleRepository.findById(id);
        if (!findArticleById.isPresent()){
            throw new NotFoundException("Not Found");
        }
        
        return findArticleById.get();
    }

    public void deleteArticle(Long id){
        articleRepository.deleteById(id);
    }

    public void saveArticle(Article article){

        articleRepository.save(article);
    }
    
    public void fillArticle(){
        
        authorService.fillAuthor();
        categoryService.fillCategory();
        
        
    }

   


    
}
