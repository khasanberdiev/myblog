package com.blog.myblog.services;

import com.blog.myblog.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import com.blog.myblog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired 
    private UserRepository userRespository;

    public List<User> userList(){
        List<User> users=(List<User>) userRespository.findAll();
        return users;
        
    }

    public Optional<User> findById(Long id){
        return userRespository.findById(id);
    }

    public void deleteUser(Long id){
        userRespository.deleteById(id);
    }

    public void saveUser(User user){
        userRespository.save(user);
    }


    
}
