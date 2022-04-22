package com.blog.myblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.blog.myblog.services.ArticleService;
import com.blog.myblog.services.CategoryService;
import com.blog.myblog.services.AuthorService;

import javax.validation.Valid;

import com.blog.myblog.models.Article;
import com.blog.myblog.models.Author;
import com.blog.myblog.models.Category;

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
        return "backoffice/article/articleForm";
    }


    @GetMapping("/edit/{id}")
    public String editArticle(@PathVariable("id") Long id, Model model){
        Article article=articleService.findById(id);
        Category category=categoryService.findById(id);
        Author author=authorService.findById(id);
        model.addAttribute("article", article);
        model.addAttribute("categories", category);
        model.addAttribute("authors", author);
        return "backoffice/article/articleForm";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute @Valid Article article){
        article.setAuthor(article.getAuthor());
        article.setCategory(article.getCategory());
        articleService.saveArticle(article);
        return "redirect:/article/";
    }

    @GetMapping("/delete/{id}")
    public String delete(Long id){
        articleService.deleteArticle(id);
        return "redirect:/article/";
    }


}
