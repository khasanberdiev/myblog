package com.blog.myblog.controllers;

import javax.validation.Valid;

import com.blog.myblog.models.Comment;
import com.blog.myblog.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/comment")
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("comment", commentService.getAll());
        return "backoffice/comment/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){
        Comment comment=commentService.findById(id).orElseThrow(()->new IllegalArgumentException("invalid id"));
        model.addAttribute("comment", comment);
        return "backoffice/comment/edit";
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("comment") @Valid Comment comment, BindingResult result, @PathVariable("id") long id){
        if(result.hasErrors()){
            comment.setId(id);
            return "backoffice/comment/edit";
        }
        commentService.save(comment);
        return "redirect:/comment/";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        Comment comment=commentService.findById(id).orElseThrow(()->new IllegalArgumentException("invalid id:"+id));
        commentService.deleteById(comment.getId());
        return "redirect:/comment/";
    }


    

    
}
