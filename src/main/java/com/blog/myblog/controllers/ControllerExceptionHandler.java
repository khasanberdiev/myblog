package com.blog.myblog.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.blog.myblog.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("backoffice/error/404Error");
        return modelAndView;
        
    }

    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("backoffice/error/400Error");
        return modelAndView;
        
    }
}
