package com.blog.myblog.controllers;


import com.blog.myblog.services.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import com.blog.myblog.models.Author;
@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("author", new Author());
        return "backoffice/author/new";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("author") @Valid Author author, BindingResult result){
        if(result.hasErrors()){
          return "/new";
        }
        authorService.save(author);
        return "redirect:/article/new";
        
    }

    

    
}
