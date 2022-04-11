package com.blog.myblog.repositories;

import com.blog.myblog.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    

}
