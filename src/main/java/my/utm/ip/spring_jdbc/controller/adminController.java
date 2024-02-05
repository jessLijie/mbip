package my.utm.ip.spring_jdbc.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import my.utm.ip.spring_jdbc.model.Bill;
import my.utm.ip.spring_jdbc.model.User;

@Controller
public class adminController {
    @Autowired
    JdbcTemplate template;

    @RequestMapping("/viewrecord")
    public ModelAndView viewrecord(@RequestParam("userid") int userid,HttpSession session) {
        ModelAndView mv = new ModelAndView("/Admin/adminviewrecord");
        String sql = "SELECT * FROM electricity WHERE userid="+userid;
        session.setAttribute("UserIDChosen",userid);
        List<Bill> billListElectricity = template.query(sql, new BeanPropertyRowMapper<>(Bill.class));
        for (Bill bill : billListElectricity) {
            bill.setbilltype("Electricity");
        }
        sql = "SELECT * FROM water WHERE userid="+userid;
        List<Bill> billListWater = template.query(sql, new BeanPropertyRowMapper<>(Bill.class));
        for (Bill bill : billListWater) {
            bill.setbilltype("Water");
        }
        sql = "SELECT * FROM recycle WHERE userid="+userid;
        List<Bill> billListRecycle = template.query(sql, new BeanPropertyRowMapper<>(Bill.class));
        for (Bill bill : billListRecycle) {
            bill.setbilltype("Recycle");
        }
        List<Bill> billList = new ArrayList<>();
        billList.addAll(billListElectricity);
        billList.addAll(billListWater);
        billList.addAll(billListRecycle);
        Collections.sort(billList, Comparator.comparing(Bill::getYear).thenComparing(Bill::getMonth));
        mv.addObject("billList", billList);
        return mv;
    }

    @RequestMapping("/applyFilterBill")
    public ModelAndView applyFilter(@RequestParam("billString") String billString,HttpSession session) {
        ModelAndView mv = new ModelAndView("/Admin/adminviewrecord");
        List<Bill> billList = new ArrayList<>();
        int userid=(int)session.getAttribute("UserIDChosen");
        if (billString.contains("electricity")) {
            String sql = "SELECT * FROM electricity WHERE userid="+userid;
            
            List<Bill> billListElectricity = template.query(sql, new BeanPropertyRowMapper<>(Bill.class));
            for (Bill bill : billListElectricity) {
                bill.setbilltype("Electricity");
            }
            billList.addAll(billListElectricity);
        }

        if (billString.contains("water")) {
            String sql = "SELECT * FROM water WHERE userid="+userid;
            List<Bill> billListWater = template.query(sql, new BeanPropertyRowMapper<>(Bill.class));
            for (Bill bill : billListWater) {
                bill.setbilltype("Water");
            }
            billList.addAll(billListWater);
        }
        if (billString.contains("recycle")) {
            String sql = "SELECT * FROM recycle WHERE userid="+userid;
            List<Bill> billListRecycle = template.query(sql, new BeanPropertyRowMapper<>(Bill.class));
            for (Bill bill : billListRecycle) {
                bill.setbilltype("Recycle");
            }
            billList.addAll(billListRecycle);
        }

        Collections.sort(billList, Comparator.comparing(Bill::getYear).thenComparing(Bill::getMonth));
        mv.addObject("billList", billList);
        return mv;
    }

    @RequestMapping("/adminDashboard")
    public ModelAndView dashboard(HttpSession session) {
        ModelAndView mv = new ModelAndView("/Admin/adminDashboard");
        int userid = (int) session.getAttribute("userid");
        mv.addObject("userid", userid);

        String sql = "Select * from user where id=?";
        List<Map<String, Object>> result = template.queryForList(sql, userid);
        Map<String, Object> user = result.get(0);
        String name = (String) user.get("username");

        String role = (String) session.getAttribute("role");
        mv.addObject("name", name);
        mv.addObject("role", role);

        sql = "SELECT * FROM user where role='user'";

        List<User> userList = template.query(sql, new BeanPropertyRowMapper<>(User.class));

        mv.addObject("userList", userList);

        return mv;
    }
}
