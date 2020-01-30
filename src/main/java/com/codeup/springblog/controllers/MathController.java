package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{var1}/to/{var2}")
    @ResponseBody
    public int add(@PathVariable int var1, @PathVariable int var2){
        return var1 + var2;
    }

    @GetMapping("/subtract/{var1}/from/{var2}")
    @ResponseBody
    public int subtract(@PathVariable int var1, @PathVariable int var2){
        return var1 - var2;
    }

    @GetMapping("/multiply/{var1}/by/{var2}")
    @ResponseBody
    public int multiply(@PathVariable int var1, @PathVariable int var2){
        return var1 * var2;
    }

    @GetMapping("/divide/{var1}/by/{var2}")
    @ResponseBody
    public int divide(@PathVariable int var1, @PathVariable int var2){
        return var1 / var2;
    }
}
