package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.bean.User;
import com.poly.service.UserService;

@Controller
@RequestMapping("/api")
public class HomeController {
    
    @RequestMapping("/")
    @ResponseBody
    public String home() {
    	
    	
        return "Hello world";
    }
    
    
    
}