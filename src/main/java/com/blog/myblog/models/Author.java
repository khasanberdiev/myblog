package com.blog.myblog.models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Author")
public class Author extends BaseEntity {
    @NotNull
    @Size(min = 1, max = 255, message = "The length of first name must be between 1 and 255")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 255, message = "The length of second name must be between 1 and 255")
    private String secondName;

    @Column(unique=true)
    @NotNull
    @Email
    private String email;
    private String image;

    private String authorInfo;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, 
            cascade = CascadeType.ALL)
    private Set<Article> articles;

   



    

    public Author(Long id, LocalDate created,  int status, String firstName, String secondName, String email, String image, String authorInfo) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.image = image;
        this.authorInfo = authorInfo;
        // this.articles = articles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    

    
    
}
