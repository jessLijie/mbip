package my.utm.ip.spring_jdbc.controller;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import my.utm.ip.spring_jdbc.model.Electricity;
import my.utm.ip.spring_jdbc.model.User;
import my.utm.ip.spring_jdbc.services.ElectricityService;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/electricity")
public class electricityController {

    @Autowired
    JdbcTemplate template;

    @Autowired
    private UserService userService;

    @Autowired
    private ElectricityService electricityService;

    @Autowired
    private AllTypeService allTypeService;

    @RequestMapping({ "/Electricity" })
    public ModelAndView mainpage(HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);
        ModelAndView modelAndView = new ModelAndView("/Electricity/InsertElectricityConsumption"); // Corrected view name
        User user = userService.getUserById(userid);
    
        modelAndView.addObject("user", user);
    
        return modelAndView;
    }

    @RequestMapping({ "/ElectricityHistory" })
    public ModelAndView historypage(HttpSession session) {
    
        ModelAndView mv = new ModelAndView("/Electricity/ElectricityHistory");
        mv.addObject("title", "List Records");
        Integer userid = (Integer) session.getAttribute("userid");
        if (userid == null) {
            // Handle case when userid is null
            // For example, redirect to login page
            mv.setViewName("redirect:/login");
            return mv;
        }
        List<Electricity> electricityList = electricityService.getElectricityByUserId(userid);

        mv.addObject("electricityList", electricityList);
        User user = electricityService.getUserById(userid);
        if (user != null) {
            mv.addObject("user", user);
        }

        return mv;
    }

    @RequestMapping("/applyFilterElectricity")
    public ModelAndView applyFilter(@RequestParam("year") int year, @RequestParam("month") String month,
            HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView mv = new ModelAndView("/Electricity/ElectricityHistory");
        String[] selectedMonths = month.split(",");
        mv.getModel().remove("electricityList");

        List<Electricity> electricityList = electricityService.filterElectricity(userid, year, selectedMonths);
        mv.addObject("electricityList", electricityList);

        return mv;
    }

    @RequestMapping("/ElectricityHistoryDetail")
    public ModelAndView historyDetail(@RequestParam("billId") int billId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Electricity/ElectricityHistoryDetail");

        Electricity electricityBill = electricityService.getElectricityById(billId);

        String period = Electricity.getPeriod(electricityBill.getMonth(), electricityBill.getYear());

        modelAndView.addObject("electricityBill", electricityBill);
        modelAndView.addObject("period", period);

        return modelAndView;
    }

    @RequestMapping("/ElectricityDownloadReport")
    public ModelAndView downloadReport(
            @RequestParam("billId") int billId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Electricity/ElectricityDownloadReport");
        User user = electricityService.getUserById(userid);

        Electricity electricityBill = electricityService.getElectricityById(billId);

        String period = Electricity.getPeriod(electricityBill.getMonth(), electricityBill.getYear());

        modelAndView.addObject("electricityBill", electricityBill);
        modelAndView.addObject("period", period);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/InsertElectricityConsumption", method = RequestMethod.GET)
    public String insertForm() {
        return "/Electricity/InsertElectricityConsumption";
    }

    @RequestMapping(value = "/InsertElectricityConsumption", method = RequestMethod.POST)
    public String insert(@RequestParam("address1") String address1,
            @RequestParam("address2") String address2,
            @RequestParam("postcode") String postcode,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("period") String period,
            @RequestParam("totalWConsumption") double totalWConsumption,
             @RequestParam("bill_img") MultipartFile billImg,
            HttpServletRequest request, HttpSession session) throws IOException {

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);

        String fullAddress = address1 + "<br>" + address2 + "<br>" + postcode + ", " + city + "<br>" + state;

        String inputdate = period;
        String split_values[] = inputdate.split("-");
        int year = Integer.parseInt(split_values[0].trim());
        int month = Integer.parseInt(split_values[1].trim());

        Integer lastElectricityId = electricityService.getMaxElectricityId();
        int billId = lastElectricityId != null ? lastElectricityId + 1 : 1;

        double carbonFootprint = totalWConsumption * 0.584;
        byte[] billImgBytes = billImg.getBytes();
        Electricity electricity = new Electricity();
        electricity.setUserid(userid);
        electricity.setId(billId);
        electricity.setAddress(fullAddress);
        electricity.setYear(year);
        electricity.setMonth(month);
        electricity.setCurrentConsumption(totalWConsumption);
        electricity.setCarbonFootprint(carbonFootprint);
        electricity.setBillImg(billImgBytes);

        electricityService.insertElectricity(electricity);

        return "redirect:/electricity/ElectricityFootprint?billId=" + electricity.getId();
    }

    @RequestMapping({ "/ElectricityFootprint" })
    public ModelAndView footprint(@RequestParam("billId") int billId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/Electricity/ElectricityFootprint");

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);
        // User user = userService.getUserById(userid);

        Electricity electricityBill = electricityService.getElectricityById(billId);

        String period = Electricity.getPeriod(electricityBill.getMonth(), electricityBill.getYear());

        double sumConsumption = allTypeService.cumulativeConsumption(userid, "electricity");
        double sumCarbonFootprint = allTypeService.cumulativeCarbonFootprint(userid, "electricity");

        modelAndView.addObject("electricityBill", electricityBill);
        modelAndView.addObject("period", period);
        modelAndView.addObject("sumConsumption", sumConsumption);
        modelAndView.addObject("sumCarbonFootprint", sumCarbonFootprint);

        return modelAndView;
    }
}