package com.blog.myblog.controllers;

import com.blog.myblog.services.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import com.blog.myblog.services.CategoryService;

@Controller
@RequestMapping("")
public class MainController{

    @Autowired
    private ArticleService articleService;
    // @Autowired
    // private CategoryService categoryService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("articles", articleService.articleList());
        // model.addAttribute("categories", categoryService.categoryList());

        return "frontoffice/main/index";
    }


}