package com.blog.myblog.models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="category")
public class Category extends BaseEntity{
    
    @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    @NotNull
    private String categoryName;
    
    @Size(min = 0, max = 50, message = "Description must be between 2 and 50 characters")
    private String categoryDescription;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, 
            cascade = CascadeType.ALL)
    private Set<Article> articles;

   
    public Set<Article> getArticles() {
        return articles;
    }
    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
    public Category(Long id, LocalDate created,  int status, String categoryName, String categoryDescription) {
        super();
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
       
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryDescription() {
        return categoryDescription;
    }
    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
       
}
