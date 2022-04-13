package com.blog.myblog.controllers;

import java.util.List;

import javax.validation.Valid;

import com.blog.myblog.models.Category;
import com.blog.myblog.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
    public String index(Model model){
        List<Category> categoryList=categoryService.categoryList();
        model.addAttribute("category", categoryList);
        return "backoffice/category/index";
    }

    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("category", new Category());
        return "backoffice/category/new";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("category") @Valid Category category, BindingResult result, Model model ){

        if(result.hasErrors()){
            model.addAttribute("category", category);
            return "backoffice/category/new";
        }
        categoryService.saveCategory(category);

        return "redirect:/category/list";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){
        Category category=categoryService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("category", category);
        return "backoffice/category/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("category") Category category, 
                        BindingResult result, @PathVariable("id") long id ){
        if(result.hasErrors()){
            category.setId(id);
            return "/category/update/{id}";
        }
        categoryService.saveCategory(category);
        return "backoffice/category/update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        Category category=categoryService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        categoryService.deleteCategoryById(category.getId());

        return "redirect:/category/list";
    }


    


    
}
