package com.ip.project.mbip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class historyviewController {

    @RequestMapping({"/historyView"})
    public String greet(){
        // model.addAttribute("showHeader", false);
        return "/Electricity/ViewDetailHistory";
    }
}