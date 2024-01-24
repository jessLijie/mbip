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
import my.utm.ip.spring_jdbc.model.Electricity;
import my.utm.ip.spring_jdbc.model.User;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/electricity")
public class electricityController {

    @Autowired
    JdbcTemplate template;

    @Autowired
    private UserService userService;
    @Autowired
    private AllTypeService allTypeService;
    
    @RequestMapping({ "/Electricity" })
    public String mainpage() {
        return "/Electricity/InsertElectricityConsumption";
    }

    @RequestMapping({ "/ElectricityHistory" })
    public ModelAndView historypage(HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView mv = new ModelAndView("/Electricity/ElectricityHistory");
        mv.addObject("title", "List Records");

        String sql = "SELECT * FROM electricity WHERE userid=" + userid;

        List<Electricity> electricityList = template.query(sql, new BeanPropertyRowMapper<>(Electricity.class));

        mv.addObject("electricityList", electricityList);
        String userSql = "SELECT * FROM user WHERE id=" + userid; 
        List<User> userList = template.query(userSql, new BeanPropertyRowMapper<>(User.class));
        if (!userList.isEmpty()) {
            mv.addObject("user", userList.get(0));
        }

        return mv;
    }

    @RequestMapping("/applyFilterElectricity")
public ModelAndView applyFilter(@RequestParam("year") int year, @RequestParam("month") String month, HttpSession session) {
    int userid = (int) session.getAttribute("userid");
    ModelAndView mv = new ModelAndView("/Electricity/ElectricityHistory");
    String[] selectedMonths = month.split(",");
    mv.getModel().remove("electricityList");

    // Use a parameterized query to prevent SQL injection
    String sql = "SELECT * FROM electricity WHERE userid = ? AND year = ? AND month IN (" + String.join(",", Collections.nCopies(selectedMonths.length, "?")) + ")";
    
    List<Object> params = new ArrayList<>();
    params.add(userid);
    params.add(year);
    params.addAll(Arrays.asList(selectedMonths));

    List<Electricity> electricityList = template.query(sql, params.toArray(), new BeanPropertyRowMapper<>(Electricity.class));
    mv.addObject("electricityList", electricityList);

    return mv;
}
   

    @RequestMapping("/ElectricityHistoryDetail")
    public ModelAndView historyDetail(@RequestParam("billId") int billId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Electricity/ElectricityHistoryDetail");
        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint FROM electricity WHERE id=?";

        Electricity electricityBill = template.queryForObject(sql, new Object[]{billId}, new BeanPropertyRowMapper<>(Electricity.class));

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
    User user = userService.getUserById(userid);

    String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint FROM electricity WHERE id=?";
    Electricity electricityBill = template.queryForObject(sql, new Object[]{billId}, new BeanPropertyRowMapper<>(Electricity.class));

    String period = Electricity.getPeriod(electricityBill.getMonth(), electricityBill.getYear());

    modelAndView.addObject("electricityBill", electricityBill);
    modelAndView.addObject("period", period);
    modelAndView.addObject("user", user);

    return modelAndView;
}


    @RequestMapping(value="/InsertElectricityConsumption", method=RequestMethod.GET)
    public String insertForm() {
        return "/Electricity/InsertElectricityConsumption";
    }

    @RequestMapping(value="/InsertElectricityConsumption", method=RequestMethod.POST)
    public String insert( @RequestParam("address1")String address1,
                            @RequestParam("address2")String address2,
                            @RequestParam("postcode")String postcode,
                            @RequestParam("city")String city,
                            @RequestParam("state")String state,
                            @RequestParam("period") String period,
                            @RequestParam("totalWConsumption") double totalWConsumption,
                            HttpServletRequest request, HttpSession session) {

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);
        
        String fullAddress = address1 + "<br>" + address2 + "<br>" + postcode + ", " + city + "<br>" + state;

        String inputdate = period;
        String split_values[] = inputdate.split("-");
        int year = Integer.parseInt(split_values[0].trim());
        int month = Integer.parseInt(split_values[1].trim());
        
        String lastElectricityIdSql = "SELECT MAX(id) FROM electricity";
        Integer lastElectricityId = template.queryForObject(lastElectricityIdSql, Integer.class);
        int billId = lastElectricityId != null ? lastElectricityId + 1 : 1;

        double carbonFootprint = totalWConsumption * 0.584 ;

        Electricity electricity = new Electricity();
        electricity.setUserid(userid);
        electricity.setId(billId);
        electricity.setAddress(fullAddress);
        electricity.setYear(year);
        electricity.setMonth(month);
        electricity.setCurrentConsumption(totalWConsumption);
        electricity.setCarbonFootprint(carbonFootprint);

        String sql = "INSERT INTO electricity (id, userid, address, year, month, currentConsumption, carbonFootprint) VALUES (?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, electricity.getId(), electricity.getUserid(), electricity.getAddress(), electricity.getYear(), electricity.getMonth(), electricity.getCurrentConsumption(), electricity.getCarbonFootprint());

        return "redirect:/electricity/ElectricityFootprint?billId=" + electricity.getId();
    }

    @RequestMapping({ "/ElectricityFootprint" })
    public ModelAndView footprint(@RequestParam("billId") int billId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/Electricity/ElectricityFootprint");

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);
        // User user = userService.getUserById(userid);

        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint FROM electricity WHERE id=?";
        Electricity electricityBill = template.queryForObject(sql, new Object[]{billId}, new BeanPropertyRowMapper<>(Electricity.class));

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
