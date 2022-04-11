package com.blog.myblog.services;

import com.blog.myblog.repositories.UserRepository;

import java.util.List;
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

    public void deleteUser(Long id){
        userRespository.deleteById(id);
    }

    public void saveUser(User user){
        userRespository.save(user);
    }

    // public List<User> userList(){
    //     return (List<User>) userRespository.findAll();
    // }
    
}
