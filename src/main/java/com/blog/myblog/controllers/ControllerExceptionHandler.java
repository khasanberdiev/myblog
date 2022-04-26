package com.blog.myblog.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

// import javax.servlet.http.HttpServletRequest;

import com.blog.myblog.exceptions.NotFoundException;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final String errorUrl="backoffice/error/404Error";

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found") 
    @ExceptionHandler(value = Exception.class)
    public ModelAndView  defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        
        if (AnnotationUtils.findAnnotation
                    (e.getClass(), ResponseStatus.class) != null)
        throw e;

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(errorUrl);
        return mav;
  }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName(errorUrl);
        return modelAndView;
        
    }

    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("backoffice/error/400Error");
        return modelAndView;
        
    }


    public String getErrorUrl() {
        return errorUrl;
    }

   
//     @ResponseStatus(HttpStatus.NOT_FOUND)
//     @ExceptionHandler(Exception.class)
//     public ModelAndView handleError() {
       
//         ModelAndView mav = new ModelAndView();
//         // mav.addObject("exception", ex);
//         // mav.addObject("url", req.getRequestURL());
//         mav.setViewName("backoffice/error/404Error");
//         return mav;
//   }
}
