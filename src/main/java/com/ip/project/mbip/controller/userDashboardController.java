package com.ip.project.mbip.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
<<<<<<< HEAD:src/main/java/com/ip/project/mbip/controller/historyviewController.java
public class userDashboardController {
   
    @RequestMapping({"/historyView"})
    public String greet(){
        // model.addAttribute("showHeader", false);
        return "/Electricity/ViewDetailHistory";}

public class userDashboardController {
    @RequestMapping("/userDashboard")
    public String dashboard(){
        return "/Dashboard/UserDashboard";
>>>>>>> c4aedba2ec05d7ad278b61d3504d2c08f3662598:src/main/java/com/ip/project/mbip/controller/userDashboardController.java
    }
}
