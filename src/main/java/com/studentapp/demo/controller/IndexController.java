package com.studentapp.demo.controller;

import org.springframework.stereotype.Controller;

@Controller
public class IndexController {
    public String showIndexPage(){

        return "index";
    }
}
