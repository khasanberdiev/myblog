package com.blog.myblog.controllers;

import java.util.List;

import javax.validation.Valid;

import com.blog.myblog.models.Comment;
import com.blog.myblog.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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

    @GetMapping({"/", ""})
    public String index(Model model){
        
        return sortedIndexPage(model, 1, 2, "id", "desc");
    }


    @GetMapping("/page/{pageNumber}")
    public String sortedIndexPage(Model model, @PathVariable("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("sortField") String sortField, @Param("sortDirection") String sortDirection){
        
        Page<Comment> page=commentService.commentPageableList(pageNumber, pageSize, sortField, sortDirection);
        List<Comment> comments=page.getContent();

        model.addAttribute("comments", comments);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("desc") ? "asc" : "desc");

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
