package com.blog.myblog.controllers;

import java.util.List;

import javax.validation.Valid;



import com.blog.myblog.models.User;

import com.blog.myblog.services.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.http.MediaType;
// import com.blog.myblog.models.UserJsonResponse;
// import java.util.List;
// import com.blog.myblog.services.UserService;
// import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String showUserList(Model model){
       return sortedIndexPage(model, 1, 2, "id", "desc");
    }

    @GetMapping("/page/{pageNumber}")
    public String sortedIndexPage(Model model, int pageNumber, int pageSize, String sortField, String sortDirection){
        
        Page<User> page=userService.userPageableList(pageNumber, pageSize, sortField, sortDirection);
        List<User> users=page.getContent();

        model.addAttribute("userList", users);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("desc") ? "asc" : "desc");

        return "backoffice/user/index";

    }

    @GetMapping("/create")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "backoffice/user/create-update-form";
    }


    @PostMapping("/save")
    public String saveOrUpdate(@Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "user/addUser";

        }
       
        userService.saveUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "backoffice/user/update";
    }

    @PostMapping("/update/{id}")
    public String saveUpdate(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") long id){
        if (bindingResult.hasErrors()){
            user.setId(id);
            return "/update";
        }
        userService.saveUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model){
        User user=userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.delete(user.getId());

        return "redirect:/user/list";
    }

        

    @PostMapping("/userSave")
    public ResponseEntity<Void> addUser(@RequestBody User user){
        System.out.println(user);
        System.out.println("000000000000000");

        userService.saveUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
        // return "redirect:/user/list";
    }

    // @RequestMapping("/add")
    // public String add(User user){
    //     return "backoffice/user/add";
    // }
    
}
