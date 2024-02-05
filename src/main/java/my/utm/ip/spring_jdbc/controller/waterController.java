package my.utm.ip.spring_jdbc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import my.utm.ip.spring_jdbc.model.User;
import my.utm.ip.spring_jdbc.model.Water;
import my.utm.ip.spring_jdbc.services.UserService;

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
    private AllTypeService allTypeService;
    
    @RequestMapping({ "/Water" })
    public ModelAndView mainpage(HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);
        ModelAndView modelAndView = new ModelAndView("/Water/InsertWaterConsumption");
        User user = userService.getUserById(userid);
    
        modelAndView.addObject("user", user);
    
        return modelAndView;
    }


    @RequestMapping({ "/WaterHistory" })
    public ModelAndView historypage(HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView mv = new ModelAndView("/Water/WaterHistory");
        mv.addObject("title", "List Records");

        String sql = "SELECT * FROM water WHERE userid=" + userid;

        List<Water> waterList = template.query(sql, new BeanPropertyRowMapper<>(Water.class));
        
        mv.addObject("waterList", waterList);
        String userSql = "SELECT * FROM user WHERE id=" + userid;
        List<User> userList = template.query(userSql, new BeanPropertyRowMapper<>(User.class));
        if (!userList.isEmpty()) {
            mv.addObject("user", userList.get(0));
        }

        return mv;
    }

    @RequestMapping("/applyFilterWater")
    public ModelAndView applyFilter(@RequestParam("year") int year, @RequestParam("month") String month, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView mv = new ModelAndView("/Water/WaterHistory");
        String[] selectedMonths = month.split(",");
        mv.getModel().remove("waterList");

        // Use a parameterized query to prevent SQL injection
        String sql = "SELECT * FROM water WHERE userid = ? AND year = ? AND month IN (" + String.join(",", Collections.nCopies(selectedMonths.length, "?")) + ")";
    
        List<Object> params = new ArrayList<>();
        params.add(userid);
        params.add(year);
        params.addAll(Arrays.asList(selectedMonths));

        List<Water> waterList = template.query(sql, params.toArray(), new BeanPropertyRowMapper<>(Water.class));
        mv.addObject("waterList", waterList);

        return mv;
    }   
    
    @RequestMapping("/WaterHistoryDetail")
    public ModelAndView historyDetail(@RequestParam("billId") int billId, HttpSession session) {
        
        ModelAndView modelAndView = new ModelAndView("/Water/WaterHistoryDetail");

        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint FROM water WHERE id=?";

        Water waterBill = template.queryForObject(sql, new Object[]{billId}, new BeanPropertyRowMapper<>(Water.class));

        String period = Water.getPeriod(waterBill.getMonth(), waterBill.getYear());

        modelAndView.addObject("waterBill", waterBill);
        modelAndView.addObject("period", period);

        return modelAndView;
    }

    @RequestMapping("/WaterDownloadReport")
    public ModelAndView downloadReport(@RequestParam("billId") int billId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Water/WaterDownloadReport");  
        User user = userService.getUserById(userid);

        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint FROM water WHERE id=?";
        Water waterBill = template.queryForObject(sql, new Object[]{billId}, new BeanPropertyRowMapper<>(Water.class));

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
                            @RequestParam("bill_img") byte[] bill_img,
                            HttpServletRequest request, HttpSession session) {

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);
        
        String fullAddress = address1 + "<br>" + address2 + "<br>" + postcode + ", " + city + "<br>" + state;

        String inputdate = period;
        String split_values[] = inputdate.split("-");
        int year = Integer.parseInt(split_values[0].trim());
        int month = Integer.parseInt(split_values[1].trim());
        
        String lastWaterIdSql = "SELECT MAX(id) FROM water";
        Integer lastWaterId = template.queryForObject(lastWaterIdSql, Integer.class);
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
        water.setBillImg(bill_img);

        String sql = "INSERT INTO water (id, userid, address, year, month, currentConsumption, carbonFootprint, bill_img) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, water.getId(), water.getUserid(), water.getAddress(), water.getYear(), water.getMonth(), water.getCurrentConsumption(), water.getCarbonFootprint(), water.getBillImg());

        return "redirect:/water/WaterFootprint?billId=" + water.getId();
    }

    @RequestMapping({ "/WaterFootprint" })
    public ModelAndView footprint(@RequestParam("billId") int billId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/Water/WaterFootprint");

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);

        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint FROM water WHERE id=?";
        Water waterBill = template.queryForObject(sql, new Object[]{billId}, new BeanPropertyRowMapper<>(Water.class));

        String period = Water.getPeriod(waterBill.getMonth(), waterBill.getYear());
        
        double sumConsumption = allTypeService.cumulativeConsumption(userid, "water");
        double sumCarbonFootprint = allTypeService.cumulativeCarbonFootprint(userid, "water");

        modelAndView.addObject("waterBill", waterBill);
        modelAndView.addObject("period", period);
        modelAndView.addObject("sumConsumption", sumConsumption);
        modelAndView.addObject("sumCarbonFootprint", sumCarbonFootprint);

        return modelAndView;
    }
}