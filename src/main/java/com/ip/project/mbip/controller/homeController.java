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
        // model.addAttribute("showHeader", false);
<<<<<<< HEAD
        return "insertWaterConsumption";
=======
        return "/Recycle/History";
>>>>>>> 24283d0fef9c65e4025f93e2d8de6c2d6bfd4078
    }
}
