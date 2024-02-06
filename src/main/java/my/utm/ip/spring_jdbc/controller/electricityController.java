package my.utm.ip.spring_jdbc.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
// import my.utm.ip.spring_jdbc.services.UserService;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/electricity")
public class electricityController {

    @Autowired
    JdbcTemplate template;
    @Autowired
    private UserSevices userServices;

    @Autowired
    private ElectricityService electricityService;

    @Autowired
    private AllTypeService allTypeService;

    @RequestMapping({ "/Electricity" })
    public ModelAndView mainpage(HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);
        ModelAndView modelAndView = new ModelAndView("/Electricity/InsertElectricityConsumption"); // Corrected view name
        User user = userServices.getUserById(userid);
    
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
        User user = userServices.getUserById(userid);
        Electricity electricityBill = electricityService.getElectricityById(billId);

        String period = Electricity.getPeriod(electricityBill.getMonth(), electricityBill.getYear());
        modelAndView.addObject("user", user);
        modelAndView.addObject("electricityBill", electricityBill);
        modelAndView.addObject("period", period);

        return modelAndView;
    }

    @RequestMapping("/ElectricityDownloadReport")
    public ModelAndView downloadReport(
            @RequestParam("billId") int billId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Electricity/ElectricityDownloadReport");
        
        System.out.println("Fetching user data for userid: " + userid);

    User user = userServices.getUserById(userid);

    // Check if user is not null before accessing properties
    if (user != null) {
        // Add logging statement to check the retrieved user data
        System.out.println("Retrieved user data: " + user.toString());

        Electricity electricityBill = electricityService.getElectricityById(billId);
        String period = Electricity.getPeriod(electricityBill.getMonth(), electricityBill.getYear());

        modelAndView.addObject("electricityBill", electricityBill);
        modelAndView.addObject("period", period);
        modelAndView.addObject("user", user);
        
    } else {
        // Handle the case where user is null
        System.out.println("User not found for userid: " + userid);
    }

        return modelAndView;
    }

    @RequestMapping(value = "/InsertElectricityConsumption", method = RequestMethod.GET)
    public String insertForm() {
        return "/Electricity/InsertElectricityConsumption";
    }

    @RequestMapping(value = "/InsertElectricityConsumptionDAta", method = RequestMethod.POST)
    public String insert(@RequestParam("address1") String address1,
            @RequestParam("address2") String address2,
            @RequestParam("postcode") String postcode,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("period") String period,
            @RequestParam("totalWConsumption") double totalWConsumption,
             @RequestParam("bill_img") MultipartFile billImg,
            HttpServletRequest request, HttpSession session, Model model) throws IOException {

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);

        String fullAddress = address1 + "<br>" + address2 + "<br>" + postcode + ", " + city + "<br>" + state;
        User user = userServices.getUserById(userid);
    
       model.addAttribute("user", user);

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

    @RequestMapping({ "/ElectricityUpdateBill" })
    public ModelAndView updatebill(@RequestParam("billId") int billId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Electricity/Editbill");
        User user = userServices.getUserById(userid);
 
        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint, bill_img FROM electricity WHERE id=?";

        List<Electricity> result = template.query(sql, new Object[] { billId }, new BeanPropertyRowMapper<>(Electricity.class));

        if (!result.isEmpty()) {
            Electricity electricityBill = result.get(0);

            String period = electricityBill.getPeriod(electricityBill.getMonth(), electricityBill.getYear());
            
            modelAndView.addObject("user", user);
            modelAndView.addObject("electricityBill", electricityBill);
            modelAndView.addObject("period", period);

            if (electricityBill.getBillImg() != null) {
                String imagedata = Base64.getEncoder().encodeToString(electricityBill.getBillImg());
                modelAndView.addObject("billimg", imagedata);
            } else {
                modelAndView.addObject("billimg", ""); // Set an empty string or some default value
            }
        } else {

            modelAndView.addObject("errorMessage", "No record found for the specified billId");
        }

        return modelAndView;
    }

    @RequestMapping("/SaveUpdateBilll")
    public ModelAndView updatebill(@RequestParam("address1") String address1,
            @RequestParam("address2") String address2,
            @RequestParam("postcode") String postcode,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("period") String period,
            @RequestParam("totalWConsumption") double totalWConsumption,
            @RequestParam("bill_img") MultipartFile bill_img,
            @RequestParam("electricityid") int id,
            HttpServletRequest request, HttpSession session) throws IOException {

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);

        String fullAddress = address1 + "<br>" + address2 + "<br>" + postcode + ", " + city + "<br>" + state;

        String inputdate = period;
        String split_values[] = inputdate.split("-");
        int year = Integer.parseInt(split_values[0].trim());
        int month = Integer.parseInt(split_values[1].trim());

        double carbonFootprint = totalWConsumption * 2.860;

        Electricity electricity = new Electricity();
        electricity.setUserid(userid);
        electricity.setId(id);
        electricity.setAddress(fullAddress);
        electricity.setYear(year);
        electricity.setMonth(month);
        electricity.setCurrentConsumption(totalWConsumption);
        electricity.setCarbonFootprint(carbonFootprint);
        if (!bill_img.isEmpty()) {
            byte[] fileBytes = bill_img.getBytes();
         electricity.setBillImg(fileBytes);
        } else {
            String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint, bill_img FROM electricity WHERE id=?";

            Electricity result = template.queryForObject(sql, new Object[]{id},
                    new BeanPropertyRowMapper<>(Electricity.class));
            if (result.getBillImg() != null) {
                electricity.setBillImg(result.getBillImg());
            } else {
                // Handle the case where result.getBillImg() is null
    }

        }

        String sql = "UPDATE electricity SET userid=?, address=?, year=?, month=?, " +
                "currentConsumption=?, carbonFootprint=?, bill_img=? WHERE id=?";
        template.update(sql, electricity.getUserid(), electricity.getAddress(), electricity.getYear(),
                electricity.getMonth(), electricity.getCurrentConsumption(),
                electricity.getCarbonFootprint(), electricity.getBillImg(), electricity.getId());

        ModelAndView mv = new ModelAndView("redirect:/electricity/ElectricityHistory");
        return mv;
    }
}