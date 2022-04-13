package com.blog.myblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.blog.myblog.services.ArticleService;
import com.blog.myblog.services.CategoryService;
import com.blog.myblog.models.Article;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model){
        
        model.addAttribute("articles", articleService.articleList());
        
        return "backoffice/article/index";
    }

    @GetMapping("/new")
    public String addArticle(Model model){
        System.out.println("0000000000000000000000");
        System.out.println(categoryService.categoryList());
        model.addAttribute("categories", categoryService.categoryList());
        model.addAttribute("article", new Article());
        return "backoffice/article/form";
    }

    @PostMapping("/save")
    public String saveArticle(Article article){
        articleService.saveArticle(article);
        return "redirect:/article/index";
    }


}
