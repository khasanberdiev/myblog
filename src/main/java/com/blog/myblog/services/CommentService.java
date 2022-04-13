package com.blog.myblog.services;

import java.util.List;
import java.util.Optional;

import com.blog.myblog.models.Comment;
import com.blog.myblog.repositories.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService{
    @Autowired
    private CommentRepository commentRepository;
    
    public List<Comment> getAll(){
        return (List<Comment>) commentRepository.findAll();
    }

    public Optional<Comment> findById(Long id){
        return commentRepository.findById(id);

    }

    public void deleteById(Long id){
        commentRepository.deleteById(id);
    }

    public void save(Comment comment){
        commentRepository.save(comment);
    }
}
