package com.ip.project.mbip.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class userDashboardController {
    @RequestMapping("/userDashboard")
    public String dashboard(){
        return "/Dashboard/UserDashboard";
    }
}
