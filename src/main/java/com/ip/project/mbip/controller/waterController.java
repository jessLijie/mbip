package com.ip.project.mbip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class waterController 
{
    @RequestMapping({"/Water"})
    public String mainpage(){
        return "/Water/WaterMainPage";
    }

    @RequestMapping({"/WaterHistory"})
    public String historypage(){
        return "/Water/WaterHistory";
    }

    @RequestMapping({"/WaterHistoryDetail"})
    public String historydetail(){
        return "/Water/WaterHistoryDetail";
    }

    @RequestMapping({"/WaterDownloadReport"})
    public String downloadreport(){
        return "/Water/WaterDownloadReport";
    }

    @RequestMapping({"/WaterFootprint"})
    public String footprint(){
        return "/Water/WaterFootprint";
    }
    
    @RequestMapping({"/InsertWaterConsumption"})
    public String insert(){
        return "/Water/InsertWaterConsumption";
    }
}
