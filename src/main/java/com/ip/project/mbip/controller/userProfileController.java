package com.ip.project.mbip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class userProfileController {
    @RequestMapping("/profile")
    public String profile(){
        return "/Profile/userProfile";
    }
}
