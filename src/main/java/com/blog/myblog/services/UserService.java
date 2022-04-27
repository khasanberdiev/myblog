package com.blog.myblog.services;

import com.blog.myblog.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import com.blog.myblog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired 
    private UserRepository userRespository;

    public List<User> userList(){
        List<User> users=(List<User>) userRespository.findAll();
        return users;
        
    }

    public Page<User> userPageableList(int pageNumber, int pageSize, String sortField, String sortDirection){
        Sort sort =sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortField).ascending():Sort.by(sortField).descending();
        Pageable pageable=PageRequest.of(pageNumber-1, pageSize, sort);
        return userRespository.findAll(pageable);
    }

    public Optional<User> findById(Long id){
        return userRespository.findById(id);
    }

    public void delete(Long id){
        userRespository.deleteById(id);
    }

    public void saveUser(User user){
        userRespository.save(user);
    }


    
}
