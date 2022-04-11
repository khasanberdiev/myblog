package com.blog.myblog.controllers;

import java.util.List;

import com.blog.myblog.models.User;
import com.blog.myblog.services.UserService;

// import java.util.List;
// import com.blog.myblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String showUserList(Model model){
        List<User> listUsers=userService.userList();

        model.addAttribute("userList", listUsers);
        model.addAttribute("newuser", new User());
        System.out.println("----------------------");
        System.out.println(listUsers);
        return "backoffice/user/index";
    }

    @GetMapping("/addUser")
    public String newUser(Model model){
        model.addAttribute("userform", new User());
        return "backoffice/user/form";
    }

    

    @PostMapping("/userSave")
    private String addUser(User user){
        System.out.println(user);
        System.out.println("000000000000000");
        userService.saveUser(user);
        return "redirect:/user/list";
    }

    // @RequestMapping("/add")
    // public String add(User user){
    //     return "backoffice/user/add";
    // }
    
}
