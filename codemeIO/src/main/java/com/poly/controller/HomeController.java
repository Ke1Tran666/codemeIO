package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class HomeController {
    
    @RequestMapping("/home")
    @ResponseBody
    public String home() {
        return "Hello world";
    }
}