package my.utm.ip.spring_jdbc.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.jdbc.Blob;

import my.utm.ip.spring_jdbc.model.User;
import my.utm.ip.spring_jdbc.services.UserService;
import my.utm.ip.spring_jdbc.model.Recycle;
import my.utm.ip.spring_jdbc.model.Recycle;
import my.utm.ip.spring_jdbc.services.ElectricityService;
import my.utm.ip.spring_jdbc.services.RecycleService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/recycle")
public class RecycleController {
    @Autowired
    JdbcTemplate template;

    @Autowired
    private RecycleService recycleService;
    @Autowired
    private AllTypeService allTypeService;

    @Autowired
    private UserService userService;

    @RequestMapping({ "/RecycleHistory" })
    public ModelAndView historypage(HttpSession session) {

        ModelAndView mv = new ModelAndView("/Recycle/RecycleHistory");
        mv.addObject("title", "List Records");
        Integer userid = (Integer) session.getAttribute("userid");
        if (userid == null) {
            // Handle case when userid is null
            // For example, redirect to login page
            mv.setViewName("redirect:/login");
            return mv;
        }
        List<Recycle> recycleList = recycleService.getRecycleByUserId(userid);

        mv.addObject("recycleList", recycleList);
        User user = recycleService.getUserById(userid);
        if (user != null) {
            mv.addObject("user", user);
        }

        return mv;
    }

    @RequestMapping("/applyFilter")
    public ModelAndView applyFilter(@RequestParam("year") int year, @RequestParam("month") String month,
            HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView mv = new ModelAndView("/Recycle/RecycleHistory");
        String[] selectedMonths = month.split(",");
        mv.getModel().remove("recycleList");

        List<Recycle> recycleList = recycleService.filterRecycle(userid, year, selectedMonths);
        mv.addObject("recycleList", recycleList);

        return mv;
    }

    @RequestMapping("/RecycleHistoryDetail")
    public ModelAndView historyDetail(@RequestParam("billId") int billId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Recycle/RecycleHistoryDetail");

        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint FROM recycle WHERE id=?";

        Recycle recycleBill = template.queryForObject(sql, new Object[] { billId },
                new BeanPropertyRowMapper<>(Recycle.class));

        String period = Recycle.getPeriod(recycleBill.getMonth(), recycleBill.getYear());

        modelAndView.addObject("recycleBill", recycleBill);
        modelAndView.addObject("period", period);

        return modelAndView;
    }

    @RequestMapping("/RecycleDownloadReport")
    public ModelAndView downloadReport(
            @RequestParam("billId") int billId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Recycle/RecycleDownloadReport");
        User user = recycleService.getUserById(userid);

        Recycle recycleBill = recycleService.getRecycleById(billId);

        String period = Recycle.getPeriod(recycleBill.getMonth(), recycleBill.getYear());

        modelAndView.addObject("recycleBill", recycleBill);
        modelAndView.addObject("period", period);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping("/Recycle")
    public ModelAndView mainpage(HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);
        ModelAndView modelAndView = new ModelAndView("/Recycle/InsertRecycleConsumption");
        User user = userService.getUserById(userid);

        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping("/InsertRecycleConsumptionDAta")
    public String insert(@RequestParam("address1") String address1,
            @RequestParam("address2") String address2,
            @RequestParam("postcode") String postcode,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("period") String period,
            @RequestParam("totalWConsumption") double totalWConsumption,
            @RequestParam("bill_img") MultipartFile bill_img,
            HttpServletRequest request, HttpSession session) throws IOException {

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);

        String fullAddress = address1 + "<br>" + address2 + "<br>" + postcode + ", " + city + "<br>" + state;

        String inputdate = period;
        String split_values[] = inputdate.split("-");
        int year = Integer.parseInt(split_values[0].trim());
        int month = Integer.parseInt(split_values[1].trim());

        String lastRecycleIdSql = "SELECT MAX(id) FROM recycle";
        Integer lastRecycleId = template.queryForObject(lastRecycleIdSql, Integer.class);
        int billId = lastRecycleId != null ? lastRecycleId + 1 : 1;

        double carbonFootprint = totalWConsumption * 2.860;

        Recycle recycle = new Recycle();
        recycle.setUserid(userid);
        recycle.setId(billId);
        recycle.setAddress(fullAddress);
        recycle.setYear(year);
        recycle.setMonth(month);
        recycle.setCurrentConsumption(totalWConsumption);
        recycle.setCarbonFootprint(carbonFootprint);
        if (!bill_img.isEmpty()) {
            byte[] fileBytes = bill_img.getBytes();
            recycle.setBillImg(fileBytes);
        }

        String sql = "INSERT INTO recycle (id, userid, address, year, month, currentConsumption, carbonFootprint, bill_img) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, recycle.getId(), recycle.getUserid(), recycle.getAddress(), recycle.getYear(),
                recycle.getMonth(), recycle.getCurrentConsumption(), recycle.getCarbonFootprint(),
                recycle.getBillImg());

        return "redirect:/recycle/RecycleFootprint?billId=" + recycle.getId();
    }

    @RequestMapping({ "/RecycleFootprint" })
    public ModelAndView footprint(@RequestParam("billId") int billId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/Recycle/RecycleFootprint");

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);
        // User user = userService.getUserById(userid);

        Recycle recycleBill = recycleService.getRecycleById(billId);

        String period = Recycle.getPeriod(recycleBill.getMonth(), recycleBill.getYear());

        double sumConsumption = allTypeService.cumulativeConsumption(userid, "recycle");
        double sumCarbonFootprint = allTypeService.cumulativeCarbonFootprint(userid, "recycle");

        modelAndView.addObject("recycleBill", recycleBill);
        modelAndView.addObject("period", period);
        modelAndView.addObject("sumConsumption", sumConsumption);
        modelAndView.addObject("sumCarbonFootprint", sumCarbonFootprint);

        return modelAndView;
    }

    @RequestMapping({ "/RecycleUpdateBill" })
    public ModelAndView updatebill(@RequestParam("billId") int billId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Recycle/Editbill");

        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint, bill_img FROM recycle WHERE id=?";

        List<Recycle> result = template.query(sql, new Object[] { billId }, new BeanPropertyRowMapper<>(Recycle.class));

        if (!result.isEmpty()) {
            Recycle recycleBill = result.get(0);

            String period = Recycle.getPeriod(recycleBill.getMonth(), recycleBill.getYear());
            User user = userService.getUserById(userid);
            modelAndView.addObject("user", user);
            modelAndView.addObject("recycleBill", recycleBill);
            modelAndView.addObject("period",recycleBill.getYear()+"-"+recycleBill.getMonth()); 
        
            

            if (recycleBill.getBillImg() != null) {
                String imagedata = Base64.getEncoder().encodeToString(recycleBill.getBillImg());
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

        Recycle recycle = new Recycle();
        recycle.setUserid(userid);
        recycle.setId(id);
        recycle.setAddress(fullAddress);
        recycle.setYear(year);
        recycle.setMonth(month);
        recycle.setCurrentConsumption(totalWConsumption);
        recycle.setCarbonFootprint(carbonFootprint);
        if (!bill_img.isEmpty()) {
            byte[] fileBytes = bill_img.getBytes();
            recycle.setBillImg(fileBytes);
        } else {
            String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint, bill_img FROM recycle WHERE id=?";

            Recycle result = template.queryForObject(sql, new Object[]{id},
                    new BeanPropertyRowMapper<>(Recycle.class));
            if (result.getBillImg() != null) {
                recycle.setBillImg(result.getBillImg());
            } else {
                // Handle the case where result.getBillImg() is null
            }

        }

        String sql = "UPDATE recycle SET userid=?, address=?, year=?, month=?, " +
                "currentConsumption=?, carbonFootprint=?, bill_img=? WHERE id=?";
        template.update(sql, recycle.getUserid(), recycle.getAddress(), recycle.getYear(),
                recycle.getMonth(), recycle.getCurrentConsumption(),
                recycle.getCarbonFootprint(), recycle.getBillImg(), recycle.getId());

                ModelAndView mv = new ModelAndView("redirect:/recycle/RecycleHistory");
                return mv;
    }
}