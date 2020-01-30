package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DeimosController {

    @GetMapping("/deimos")
    public String deimos(){
        return "deimos";
    }


    @GetMapping("/deimos/{numberOfDays}")
    public String deimos(@PathVariable int numberOfDays, Model model){
        return "deimos";
    }

    @PostMapping("/deimos")
    public String deimosPost(@RequestParam int numberOfDays, Model model){
        model.addAttribute("numberOfDays",numberOfDays);
        return "deimos";
    }
}
