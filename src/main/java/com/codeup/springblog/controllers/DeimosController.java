package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeimosController {

    @GetMapping("/deimos")
    @ResponseBody

    public String deimos(){
        return "There are 31 days remaining";
    }
}
