package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class DiceRollController {

    @GetMapping("/roll-dice")
    public String diceRollGet(){
        return "diceroll";
    }

    @PostMapping("/roll-dice")
    public String diceRollPost(@RequestParam int number, Model model){
        Random rand = new Random();
        int actual = rand.nextInt((6 - 1) + 1) + 1;
        model.addAttribute("guess",number);
        model.addAttribute("actual",actual);
        return "guesspage";
    }

    @PostMapping("/roll-dice/multiple")
    public String diceRollPost(@RequestParam int diceRolls,@RequestParam int number, Model model){
        Random rand = new Random();
        List<Integer> actuals = new ArrayList<>();
        for(int i = 0; i < diceRolls; i++){
            actuals.add(rand.nextInt((6 - 1) + 1) + 1);
        }
        model.addAttribute("actuals",actuals);
        model.addAttribute("guess",number);
        return "guesspagemultiple";
    }
}
