package com.blog.myblog.repositories;
import com.blog.myblog.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long>{
    
}
