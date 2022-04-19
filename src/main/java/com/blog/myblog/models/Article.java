package com.blog.myblog.models;
import java.time.LocalDate;
// import java.util.Set;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="article")
public class Article extends BaseEntity {
    @NotNull
    @Size(min=1, max=255, message="Title length must be between 1 and 255 charachters ")
    private String title;
    @NotNull
    private String body;
    private String tags;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="author_id", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, 
            cascade = CascadeType.ALL)
    private Set <Comment> comment;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="category_id", nullable = false)
    private Category category;
    

   
    public Article(Long id, LocalDate created,  int status, String title, String body, String tags, Author author, Category category) {
        this.title = title;
        this.body = body;
        this.tags = tags;
        this.author = author;
        // this.comment = comment;
        this.category = category;
    }

    public Article(){
        
    }

    
    public Set<Comment> getComment() {
        return comment;
    }


    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }


    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
  
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    // public Set<Comment> getComment() {
    //     return comment;
    // }
    // public void setComment(Set<Comment> comment) {
    //     this.comment = comment;
    // }

    
}
