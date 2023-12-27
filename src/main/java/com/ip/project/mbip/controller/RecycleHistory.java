package com.ip.project.mbip.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecycleHistory {

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