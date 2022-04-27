package com.blog.myblog.controllers;


import com.blog.myblog.services.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.validation.Valid;

import com.blog.myblog.models.Author;
@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping({"/list", "/"})
    public String index(Model model){
        return sortedIndexPage(model, 1, 2, "id", "desc");
    }


    @GetMapping("/page/{pageNumber}")
    public String sortedIndexPage(Model model, @PathVariable("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("sortField") String sortField, @Param("sortDirection") String sortDirection){
        
        Page<Author> page=authorService.authorPageableList(pageNumber, pageSize, sortField, sortDirection);
        List<Author> authors=page.getContent();

        model.addAttribute("authors", authors);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("desc") ? "asc" : "desc");

        return "backoffice/author/index";

    }

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
