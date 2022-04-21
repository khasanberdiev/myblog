package com.blog.myblog.services;

import java.util.List;
// import java.util.Optional;

import com.blog.myblog.repositories.AuthorRespository;
import com.blog.myblog.models.Author;
// import com.blog.myblog.models.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRespository authorRepository;

    public List<Author> authorList(){
        return (List<Author>) authorRepository.findAll();
    }

    public void save(Author author){
        authorRepository.save(author);
    }

    public Author findById(Long id){
        return authorRepository.findById(id).orElse(null);
    }
    
    
}
