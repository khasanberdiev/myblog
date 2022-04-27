package com.blog.myblog.repositories;
import com.blog.myblog.models.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>{
    
}
