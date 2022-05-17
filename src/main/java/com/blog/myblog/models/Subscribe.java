package com.blog.myblog.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
@Table(name="subscribe")
public class Subscribe extends BaseEntity {

    @Email
    @NonNull
    @Column(unique = true)
    @Size(min = 2, max = 50, message = "Email length should be between 4 and 50 characters")
    private String email;

    public Subscribe(
            @Email @Size(min = 2, max = 50, message = "Email length should be between 4 and 50 characters") String email) {
        this.email = email;
    }

    public Subscribe(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
