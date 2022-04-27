package com.blog.myblog.controllers;

import java.util.List;

import javax.validation.Valid;

import com.blog.myblog.models.Category;
import com.blog.myblog.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
// import org.springframework.data.repository.query.Param;
// import org.springframework.data.web.PageableDefault;
// import org.springframework.data.domain.Sort;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
    public String index(Model model){
      
        return sortedIndexPage(model, 1,  "id", "desc", 2);
    }

    @GetMapping("/page/{pageNumber}")
    public String sortedIndexPage(Model model, @PathVariable("pageNumber") int pageNumber, @RequestParam("sortField") String sortField, @RequestParam("sortDirection") String sortDirection, @RequestParam("pageSize") int pageSize){
        Page<Category> page=categoryService.categoryPageableList(pageNumber, pageSize, sortField, sortDirection);
        List<Category> categoryList= page.getContent();
        model.addAttribute("category", categoryList);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("desc") ? "asc" : "desc");

        return "backoffice/category/index";
    }

    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("category", new Category());
        return "backoffice/category/categoryForm";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@ModelAttribute @Valid Category category, BindingResult result, Model model ){

        if(result.hasErrors()){
            model.addAttribute("category", category);
            return "backoffice/category/categoryForm";
        }
        categoryService.saveCategory(category);

        return "redirect:/category/list";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){
        Category category=categoryService.findById(id);
        model.addAttribute("category", category);
        return "backoffice/category/categoryForm";
    }

    // @PostMapping("/update/{id}")
    // public String update(@ModelAttribute("category") Category category, 
    //                     BindingResult result, @PathVariable("id") long id ){
    //     if(result.hasErrors()){
    //         category.setId(id);
    //         return "/category/update/{id}";
    //     }
    //     categoryService.saveCategory(category);
    //     return "backoffice/category/update";
    // }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        Category category=categoryService.findById(id);
        categoryService.deleteCategoryById(category.getId());
        return "redirect:/category/list";
    }

    

    


    


    
}
