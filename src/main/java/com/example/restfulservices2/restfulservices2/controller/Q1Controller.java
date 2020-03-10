package com.example.restfulservices2.restfulservices2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Q1Controller {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/greeting")
    public String greet(String username){
        return messageSource.getMessage("greetings.message", null, LocaleContextHolder.getLocale());
    }

    @GetMapping(path = "/greeting-username")
    public String messageWithUserName(@RequestParam("username") String username){
        return messageSource.getMessage("greetings.message", null,
                LocaleContextHolder.getLocale()) + " "+ username;
    }

}
