package com.blog.myblog.repositories;

import com.blog.myblog.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    

}
