package com.blog.myblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.myblog.services.ArticleService;
import com.blog.myblog.services.CategoryService;
import com.blog.myblog.services.AuthorService;

import java.util.List;

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

  
    @GetMapping({"/list", ""})
    public String index(Model model, String searchQuery, String searchFilter, String searchStatus){
        return sortedIndexPage(model, 1, 5, "id", "desc", searchQuery, searchFilter, searchStatus);
    }

    @GetMapping("/page/{pageNumber}")
    public String sortedIndexPage(Model model,
                                   @PathVariable("pageNumber") int pageNumber,
                                   @RequestParam("pageSize") int pageSize, 
                                   @RequestParam("sortField") String sortField,
                                   @RequestParam("sortDirection") String sortDirection,
                                   @RequestParam("searchQuery") String searchQuery,
                                   @RequestParam("searchFilter") String searchFilter,
                                   @RequestParam("searchStatus") String searchStatus  

                                   ){
        
      
        Page<Article> page=articleService.articleSearchPageableList(pageNumber,
                                                                    pageSize, 
                                                                    sortField,
                                                                    sortDirection, 
                                                                    searchQuery,
                                                                    searchFilter,
                                                                    searchStatus);
        List<Article> articles=page.getContent();

        model.addAttribute("articles", articles);
        model.addAttribute("searchQuery", searchQuery);
        model.addAttribute("searchFilter",searchFilter);
        model.addAttribute("searchStatus",searchStatus);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("desc") ? "asc" : "desc");

        return "backoffice/article/index";

    }

    @GetMapping("/new")
    public String addArticle(Model model){
        
        model.addAttribute("authors", authorService.authorList());
        model.addAttribute("categories", categoryService.categoryList());
        model.addAttribute("article", new Article());
        return "backoffice/article/articleForm";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") long id){
        model.addAttribute("authors", authorService.authorList());
        model.addAttribute("categories", categoryService.categoryList());
        model.addAttribute("article", articleService.findById(id));
        return "backoffice/article/view";
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
        return "redirect:/article/view/"+article.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(Long id){
        articleService.deleteArticle(id);
        return "redirect:/article/";
    }


}
