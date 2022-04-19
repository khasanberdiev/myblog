package com.blog.myblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.blog.myblog.services.ArticleService;
import com.blog.myblog.services.CategoryService;
import com.blog.myblog.services.AuthorService;

import javax.validation.Valid;

import com.blog.myblog.models.Article;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/")
    public String index(Model model){
        
        model.addAttribute("articles", articleService.articleList());
        
        return "backoffice/article/index";
    }

    @GetMapping("/new")
    public String addArticle(Model model){
        model.addAttribute("authors", authorService.authorList());
        model.addAttribute("categories", categoryService.categoryList());
        model.addAttribute("article", new Article());
        return "backoffice/article/form";
    }

    @PostMapping("/save")
    public String saveArticle(@ModelAttribute @Valid Article article){
        
        article.setAuthor(article.getAuthor());
        article.setCategory(article.getCategory());
        articleService.saveArticle(article);
        System.out.println(articleService.articleList());
        return "redirect:/article/";
    }


}
