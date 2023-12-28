package com.ip.project.mbip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class homeController {
   
    @RequestMapping({"/hello"})
    public String greet(@RequestParam String name, Model model){
        model.addAttribute("name",name);
        return "welcome";
    }

    
    @RequestMapping({"/login"})
    public String login(){
        return "welcome";
    }
}
