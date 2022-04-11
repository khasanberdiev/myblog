package com.blog.myblog.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="comment")
public class Comment extends BaseEntity {

    @Size(max=255, message = "Full name length cannot be more than 255 words")
    @NotNull
    private String fullName;

    @Email(message = "Please insert valid email")
    @NotNull
    private String email;

    @NotNull
    @Size(min = 5, max = 500, message = "Your comment must between 5 and 500 characters")
    private String message;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="article_id", nullable = false)
    private Article article; 

    
    public Comment(Long id, LocalDate created,  int status, String fullName, String email, String message) {
        super();
        this.fullName = fullName;
        this.email = email;
        this.message = message;
       
    }

    
    public Article getArticle() {
        return article;
    }


    public void setArticle(Article article) {
        this.article = article;
    }


    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
   
    
    
}
