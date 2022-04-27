package com.blog.myblog.services;

import java.util.List;
import java.util.Optional;

import com.blog.myblog.models.Comment;
import com.blog.myblog.repositories.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommentService{
    @Autowired
    private CommentRepository commentRepository;
    
    public List<Comment> getAll(){
        return (List<Comment>) commentRepository.findAll();
    }

    public Page<Comment> commentPageableList(int pageNumber, int pageSize, String sortField, String sortDirection){
        Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortField).ascending():Sort.by(sortField).descending();
        Pageable pageable=PageRequest.of(pageNumber-1, pageSize, sort);
        return commentRepository.findAll(pageable);
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
