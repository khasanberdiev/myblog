package com.blog.myblog.controllers;

import java.util.List;

import javax.validation.Valid;

import com.blog.myblog.models.Article;
import com.blog.myblog.models.Subscribe;
import com.blog.myblog.models.custom.ArticleArchive;
import com.blog.myblog.models.custom.CategoryCountInterface;
import com.blog.myblog.services.ArticleService;
// import com.blog.myblog.services.CategoryService;
import com.blog.myblog.services.SubscribeService;

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

@Controller
@RequestMapping("")
public class MainController{

    @Autowired
    private ArticleService articleService;

    // @Autowired
    // private CategoryService categoryService;

    @Autowired
    private SubscribeService subscribeService;

    private final static int PAGE_NUMBER=1;
    private final static int PAGE_SIZE=2;


    @GetMapping({"","/list"})
    public String index(Model model, String searchQuery, Integer filterByCategory ){

        return pagedIndex(model, PAGE_NUMBER, PAGE_SIZE, searchQuery, filterByCategory);
    }

    @GetMapping("/page/{pageNumber}")
    public String pagedIndex(Model model, 
                            @PathVariable("pageNumber") int pageNumber, 
                            @RequestParam("pageSize") int pageSize, 
                            @RequestParam("searchQuery") String searchQuery,
                            @RequestParam("filterByCategory") Integer filterByCategory){

        Page<Article> page=articleService.activeArticleList(pageNumber, pageSize, searchQuery, filterByCategory);
        List<Article> articleList= page.getContent();
        List<Article> topArticles=articleService.getTopArticles();
        List<CategoryCountInterface> categoryAndCount=articleService.getCategoryAndCount();
        List<ArticleArchive> articleByMonth=articleService.articleArchive();
        for(ArticleArchive i: articleByMonth){
            System.out.println("testing");
            System.out.println(i.getMonth());
            System.out.println(i.getArticleMonthlyCount());
        }
      

        model.addAttribute("subscribe", new Subscribe());
        model.addAttribute("topArticles", topArticles);
        model.addAttribute("articleByMonth", articleByMonth);
        model.addAttribute("categories", categoryAndCount);
        model.addAttribute("articles", articleList);
        model.addAttribute("searchQuery", searchQuery);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalElements", page.getTotalElements());
        return "frontoffice/main/index";
    }

    @PostMapping("/subscribe")
    public String subscribe(@ModelAttribute @Valid Subscribe subscribe){
        subscribeService.save(subscribe);
        return "redirect:/";
    }

    @GetMapping("/view/{id}")
    public String viewArticle(@PathVariable("id") Long id, Model model){

        Article article=articleService.findById(id);
        int viewCount=article.getViewCount();
        article.setViewCount(viewCount+1);
        model.addAttribute("article", article);
        return "frontoffice/main/article";
    }



    @GetMapping({"about", "aboutus", "aboutUs"})
    public String aboutUs(){
        return "frontoffice/main/aboutus";
    }

    @GetMapping({"contact", "contacts", "contactus", "contactUs"})
    public String contact(){
        return "frontoffice/main/contact";
    }


    public  int findCategoryCount(){
        
        return 1;
        //  categoryService.categoryCountById(category_id);
    }


}