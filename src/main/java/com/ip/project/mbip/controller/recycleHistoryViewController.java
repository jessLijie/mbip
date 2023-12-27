package com.ip.project.mbip.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class recycleHistoryViewController {

    @RequestMapping({"/Recycle"})
    public String mainpage(){
        return "/Recycle/RecycleMainPage";
    }
    @RequestMapping({"/RecycleHistory"})
    public String historypage(){
        return "/Recycle/History";
    }
    @RequestMapping({"/RecycleHistoryView"})
    public String historydetail(){
        return "/Recycle/ViewDetailHistory";
    }

    @RequestMapping({"/RecycleDownloadReport"})
    public String downloadreport(){
        return "/Recycle/downloadreport";
    }
    @RequestMapping({"/InsertRecycleConsumption"})
    public String insert(){
        return "/Recycle/InsertRecycleConsumption";
    }

     @RequestMapping({"/RecycleFootprint"})
    public String footpring(){
        return "/Recycle/RecycleFootprint";
    }
}