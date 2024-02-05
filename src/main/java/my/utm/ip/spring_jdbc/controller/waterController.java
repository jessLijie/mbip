package my.utm.ip.spring_jdbc.controller;

import java.io.IOException;
import java.util.Base64;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import my.utm.ip.spring_jdbc.model.User;
import my.utm.ip.spring_jdbc.model.Water;
import my.utm.ip.spring_jdbc.services.UserService;
import my.utm.ip.spring_jdbc.services.WaterService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/water")
public class waterController 
{
    @Autowired
    JdbcTemplate template;

    @Autowired
    private UserService userService;

    @Autowired
    private WaterService waterService;

    @Autowired
    private AllTypeService allTypeService;
    
    @RequestMapping({ "/Water" })
    public ModelAndView mainpage(HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);
        ModelAndView modelAndView = new ModelAndView("/Water/InsertWaterConsumption");
        User user = waterService.getUserById(userid); 
    
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping({ "/WaterHistory" })
    public ModelAndView historypage(HttpSession session) {
        ModelAndView mv = new ModelAndView("/Water/WaterHistory");
        mv.addObject("title", "List Records");
        Integer userid = (Integer) session.getAttribute("userid");
        if (userid == null) {
            // Handle case when userid is null
            // For example, redirect to login page
            mv.setViewName("redirect:/login");
            return mv;
        }
        List<Water> waterList = waterService.getWaterByUserId(userid);
        
        mv.addObject("waterList", waterList);
        User user = waterService.getUserById(userid);
        if (user != null) {
            mv.addObject("user", user);
        }

        return mv;
    }

    @RequestMapping("/applyFilterWater")
    public ModelAndView applyFilter(@RequestParam("year") int year, @RequestParam("month") String month, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView mv = new ModelAndView("/Water/WaterHistory");
        String[] selectedMonths = month.split(",");
        mv.getModel().remove("waterList");

        List<Water> waterList = waterService.filterWater(userid, year, selectedMonths);
        mv.addObject("waterList", waterList);

        return mv;
    }   
    
    @RequestMapping("/WaterHistoryDetail")
    public ModelAndView historyDetail(@RequestParam("billId") int billId, HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView("/Water/WaterHistoryDetail");

        Water waterBill = waterService.getWaterById(billId);

        String period = Water.getPeriod(waterBill.getMonth(), waterBill.getYear());

        modelAndView.addObject("waterBill", waterBill);
        modelAndView.addObject("period", period);

        return modelAndView;
    }

    @RequestMapping("/WaterDownloadReport")
    public ModelAndView downloadReport(@RequestParam("billId") int billId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Water/WaterDownloadReport");  
        User user = waterService.getUserById(userid);

        Water waterBill = waterService.getWaterById(billId);

        String period = Water.getPeriod(waterBill.getMonth(), waterBill.getYear());

        modelAndView.addObject("waterBill", waterBill);
        modelAndView.addObject("period", period);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value="/InsertWaterConsumption", method=RequestMethod.GET)
    public String insertForm() {
        return "/Water/InsertWaterConsumption";
    }

    @RequestMapping(value="/InsertWaterConsumption", method=RequestMethod.POST)
    public String insert( @RequestParam("address1")String address1,
                            @RequestParam("address2")String address2,
                            @RequestParam("postcode")String postcode,
                            @RequestParam("city")String city,
                            @RequestParam("state")String state,
                            @RequestParam("period") String period,
                            @RequestParam("totalWConsumption") double totalWConsumption,
                            @RequestParam("bill_img") MultipartFile bill_img,
                            HttpServletRequest request, HttpSession session) throws IOException{

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);
        
        String fullAddress = address1 + "<br>" + address2 + "<br>" + postcode + ", " + city + "<br>" + state;

        String inputdate = period;
        String split_values[] = inputdate.split("-");
        int year = Integer.parseInt(split_values[0].trim());
        int month = Integer.parseInt(split_values[1].trim());
        
        Integer lastWaterId = waterService.getMaxWaterId();
        int billId = lastWaterId != null ? lastWaterId + 1 : 1;

        double prorataFactor = 1.03333;
        double carbonFootprint = totalWConsumption * 0.419 * prorataFactor;

        Water water = new Water();
        water.setUserid(userid);
        water.setId(billId);
        water.setAddress(fullAddress);
        water.setYear(year);
        water.setMonth(month);
        water.setCurrentConsumption(totalWConsumption);
        water.setCarbonFootprint(carbonFootprint);
        if (!bill_img.isEmpty()) {
            byte[] fileBytes = bill_img.getBytes();
            water.setBillImg(fileBytes);
        }

        waterService.insertWater(water);

        return "redirect:/water/WaterFootprint?billId=" + water.getId();
    }

    @RequestMapping({ "/WaterFootprint" })
    public ModelAndView footprint(@RequestParam("billId") int billId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/Water/WaterFootprint");

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);

        Water waterBill = waterService.getWaterById(billId);

        String period = Water.getPeriod(waterBill.getMonth(), waterBill.getYear());
        
        double sumConsumption = allTypeService.cumulativeConsumption(userid, "water");
        double sumCarbonFootprint = allTypeService.cumulativeCarbonFootprint(userid, "water");

        modelAndView.addObject("waterBill", waterBill);
        modelAndView.addObject("period", period);
        modelAndView.addObject("sumConsumption", sumConsumption);
        modelAndView.addObject("sumCarbonFootprint", sumCarbonFootprint);

        return modelAndView;
    }

    @RequestMapping({ "/WaterUpdateBill" })
    public ModelAndView updatebill(@RequestParam("billId") int billId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Water/WaterEditBill");

        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint, bill_img FROM water WHERE id=?";

        List<Water> result = template.query(sql, new Object[] { billId }, new BeanPropertyRowMapper<>(Water.class));

        if (!result.isEmpty()) {
            Water waterBill = result.get(0);

            String period = Water.getPeriod(waterBill.getMonth(), waterBill.getYear());
            User user = userService.getUserById(userid);
            modelAndView.addObject("user", user);
            modelAndView.addObject("waterBill", waterBill);
            modelAndView.addObject("period", period);

            if (waterBill.getBillImg() != null) {
                String imagedata = Base64.getEncoder().encodeToString(waterBill.getBillImg());
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
            @RequestParam("recycleid") int id,
            HttpServletRequest request, HttpSession session) throws IOException {

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);

        String fullAddress = address1 + "<br>" + address2 + "<br>" + postcode + ", " + city + "<br>" + state;

        String inputdate = period;
        String split_values[] = inputdate.split("-");
        int year = Integer.parseInt(split_values[0].trim());
        int month = Integer.parseInt(split_values[1].trim());

        double carbonFootprint = totalWConsumption * 2.860;

        Water water = new Water();
        water.setUserid(userid);
        water.setId(id);
        water.setAddress(fullAddress);
        water.setYear(year);
        water.setMonth(month);
        water.setCurrentConsumption(totalWConsumption);
        water.setCarbonFootprint(carbonFootprint);
        if (!bill_img.isEmpty()) {
            byte[] fileBytes = bill_img.getBytes();
            water.setBillImg(fileBytes);
        } else {
            String sql = "select bill_img from water where id=" + id;
            byte[] fileBytes = template.queryForObject(sql, new Object[] { id }, byte[].class);
            water.setBillImg(fileBytes);
        }

        String sql = "UPDATE water SET userid=?, address=?, year=?, month=?, " +
                "currentConsumption=?, carbonFootprint=?, bill_img=? WHERE id=?";
        template.update(sql, water.getUserid(), water.getAddress(), water.getYear(),
                water.getMonth(), water.getCurrentConsumption(),
                water.getCarbonFootprint(), water.getBillImg(), water.getId());

        ModelAndView mv = new ModelAndView("/Water/WaterHistory");
        return mv;
    }
}