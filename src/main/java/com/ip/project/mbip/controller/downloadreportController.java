package com.ip.project.mbip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class downloadreportController {
    
    @RequestMapping({"/downloadreport"})
    public String greet(){
        // model.addAttribute("showHeader", false);
        return "/Electricity/downloadreport";
    }
}

