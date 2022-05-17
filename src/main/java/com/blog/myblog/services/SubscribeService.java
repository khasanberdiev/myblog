package com.blog.myblog.services;

import java.util.List;

import com.blog.myblog.models.Subscribe;
import com.blog.myblog.repositories.SubscribeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscribeService{
    @Autowired
    private SubscribeRepository subscribeRepository;

    public void save(Subscribe subscribe){
        subscribeRepository.save(subscribe);
    }

    public List<Subscribe> findAll(){
        return (List<Subscribe>) subscribeRepository.findAll();
    }
    
}
