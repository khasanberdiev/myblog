package com.blog.myblog.controllers;

// import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.blog.myblog.models.Category;
import com.blog.myblog.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Slice;
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
    public String index(String searchQuery, String searchFilter, String searchStatus, Model model){
        categoryService.fillCategory();

        return sortedIndexPage(model, 1,  "id", "desc", 2, searchQuery, searchFilter, searchStatus);
    }

    @GetMapping("/page/{pageNumber}")
    public String sortedIndexPage(Model model, @PathVariable("pageNumber") int pageNumber, @RequestParam("sortField") String sortField, @RequestParam("sortDirection") String sortDirection,
            @RequestParam("pageSize") int pageSize, @RequestParam("searchQuery") String searchQuery, @RequestParam("searcFilter") String searchFilter, @RequestParam("searchStatus") String searchStatus){
    
        
        // Page<Category> page=categoryService.categoryPageableList(pageNumber, pageSize, sortField, sortDirection);
        // List<Category> categoryList= page.getContent();

        Page<Category> page=categoryService.findByCategoryName(pageNumber, pageSize, sortField, sortDirection,  searchQuery, searchFilter, searchStatus);
        List<Category> categoryList= page.getContent();
        
        
        
        model.addAttribute("category", categoryList);
        model.addAttribute("searchQuery",searchQuery);
        model.addAttribute("searchFilter",searchFilter);
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

        return "redirect:/category/view/"+category.getId();

    }

    @PostMapping("/saveAndNew")
    public String saveAndNew(@ModelAttribute @Valid Category category, BindingResult result, Model model ){

        if(result.hasErrors()){
            model.addAttribute("category", category);
            return "backoffice/category/categoryForm";
        }
        categoryService.saveCategory(category);

        model.addAttribute("category", new Category());
        return "backoffice/category/categoryForm";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){
        Category category=categoryService.findById(id);
        model.addAttribute("category", category);
        return "backoffice/category/categoryForm";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") long id){
        model.addAttribute("category", categoryService.findById(id));
        return "backoffice/category/view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        Category category=categoryService.findById(id);
        categoryService.deleteCategoryById(category.getId());
        return "redirect:/category/list";
    }

    

    


    


    
}
