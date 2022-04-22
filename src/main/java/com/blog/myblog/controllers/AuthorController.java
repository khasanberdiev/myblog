package com.blog.myblog.controllers;


import com.blog.myblog.services.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "backoffice/author/authorForm";
    }

    @GetMapping("/edit/{id}")
    public String add(@PathVariable("id") Long id, BindingResult result, Model model){
        if (result.hasErrors()){
            return "/new";
        }
        Author author=authorService.findById(id);
        authorService.save(author);
        return "backoffice/author/authorForm";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("author") @Valid Author author, BindingResult result){
        if(result.hasErrors()){
          return "/new";
        }
        authorService.save(author);
        return "redirect:/article/new";
        
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteById(id);
        return "redirect:/author";
    }

    

    
}
