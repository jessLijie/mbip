package com.ip.project.mbip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class electricityHistoryViewController {

    @RequestMapping({"/Electricity"})
    public String mainpage(){
        return "/Electricity/ElectricityMainPage";
    }
    @RequestMapping({"/ElectricityHistory"})
    public String historypage(){
        return "/Electricity/History";
    }
    @RequestMapping({"/ElectricityHistoryView"})
    public String historydetail(){
        return "/Electricity/ViewDetailHistory";
    }

    @RequestMapping({"/ElectricityDownloadReport"})
    public String downloadreport(){
        return "/Electricity/downloadreport";
    }
    @RequestMapping({"/ElectricityFootprint"})
    public String footpring(){
        return "/Electricity/ElectricityFootprint";
    }
    
    @RequestMapping({"/InsertElectricityConsumption"})
    public String insert(){
        return "/Electricity/InsertElectricityConsumption";
    }

    
}