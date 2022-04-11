package com.blog.myblog.models;

import java.time.LocalDate;
import java.util.Set;

// import javax.persistence.Column;
import javax.persistence.Entity;
// import javax.persistence.Table;
import javax.persistence.ManyToMany;

@Entity
public class User extends BaseEntity {
    // @Column(nullable = false, unique = true)
    private String email;
    // @Column(nullable = false, length = 25, name = "first_name")
    private String firstName;
    // @Column(nullable = false, length = 25, name = "last_name")
    private String lastName;
    // @Column(nullable = false, unique = true, length = 25)
    private String userName;
    // @Column(nullable = false, length = 25)
    private String password;

    @ManyToMany
    private Set<Article>articles;

    
       
    public User(Long id, LocalDate created,  int status, String email, String firstName, String lastName, String userName, String password) {
        super();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }
    public User(){
        
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
   
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
