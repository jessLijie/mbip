package my.utm.ip.spring_jdbc.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import my.utm.ip.spring_jdbc.model.Bill;
import my.utm.ip.spring_jdbc.model.Recycle;

@Controller
public class userProfileController {
    @Autowired
    JdbcTemplate template;

   
    @RequestMapping("/profile")
    public ModelAndView profile(HttpSession session) {
        ModelAndView mv = new ModelAndView("/Profile/userProfile");
        int userid = (int) session.getAttribute("userid");
        mv.addObject("userid", userid);

        String sql = "Select * from user where id=?";
        List<Map<String, Object>> result = template.queryForList(sql, userid);
        Map<String, Object> user = result.get(0);
        String name = (String) user.get("username");
        String fullname = (String) user.get("fullname");
        Date birthdate = (Date) user.get("birthdate");
        String email = (String) user.get("email");
        String matricsNo = (String) user.get("matricsNo");
        String password = (String) user.get("password");
        String phone = (String) user.get("phone");
        String add1 = (String) user.get("add1");
        String add2 = (String) user.get("add2");
        String zipcode = (String) user.get("zipcode");
        String state = (String) user.get("state");
        String city = (String) user.get("city");
        String role = (String) session.getAttribute("role");
        mv.addObject("name", name);
        mv.addObject("fullname", fullname);
        mv.addObject("birthdate", birthdate);
        mv.addObject("email", email);
        mv.addObject("password", password);
        mv.addObject("phone", phone);
        mv.addObject("matricsNo", matricsNo);
        mv.addObject("add1", add1);
        mv.addObject("add2", add2);
        mv.addObject("zipcode", zipcode);
        mv.addObject("state", state);
        mv.addObject("city", city);
        mv.addObject("role", role);

        return mv;
    }

    @RequestMapping("/profile/insert")
    public String profileEdit(@RequestParam("username") String un,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("matricsNo") String matricsNo,
            HttpSession session) {
        String sql = "INSERT INTO user (username, password, email, matricsNo,role) VALUES (?,?,?,?,?)";
        template.update(sql, un, password, email, matricsNo,"user");
        session.setAttribute("username", un);
        return "redirect:/profile/view";
    }

    @RequestMapping("/profile/view")
    public ModelAndView profileView(HttpSession session) {
        ModelAndView mv = new ModelAndView("/Profile/userProfile");

        String username = (String) session.getAttribute("username");
        System.out.println(username);

        String sql = "SELECT id FROM User WHERE username=?";
        Map<String, Object> user = template.queryForMap(sql, username);

        int userid = (int) user.get("id");
        session.setAttribute("userid", userid);
        System.out.println(userid);

        sql = "Select * from user where id=?";
        List<Map<String, Object>> result = template.queryForList(sql, userid);
        user = result.get(0);
        String name = (String) user.get("username");
        String email = (String) user.get("email");
        String password = (String) user.get("password");
        String matricsNo = (String) user.get("matricsNo");
        mv.addObject("name", name);
        mv.addObject("email", email);
        mv.addObject("password", password);
        mv.addObject("matricsNo", matricsNo);
        return mv;
    }

    @RequestMapping("/profile/edit")
    public ModelAndView edit(HttpSession session) {
        ModelAndView mv = new ModelAndView("/Profile/editUserProfile");
        int userid = (int) session.getAttribute("userid");
        mv.addObject("userid", userid);

        String sql = "Select * from user where id=?";
        List<Map<String, Object>> result = template.queryForList(sql, userid);
        Map<String, Object> user = result.get(0);
        String name = (String) user.get("username");
        String fullname = (String) user.get("fullname");
        Date birthdate = (Date) user.get("birthdate");
        String email = (String) user.get("email");
        String matricsNo = (String) user.get("matricsNo");
        String password = (String) user.get("password");
        String phone = (String) user.get("phone");
        String add1 = (String) user.get("add1");
        String add2 = (String) user.get("add2");
        String zipcode = (String) user.get("zipcode");
        String state = (String) user.get("state");
        String city = (String) user.get("city");
        mv.addObject("name", name);
        mv.addObject("fullname", fullname);
        mv.addObject("birthdate", birthdate);
        mv.addObject("email", email);
        mv.addObject("password", password);
        mv.addObject("phone", phone);
        mv.addObject("matricsNo", matricsNo);
        mv.addObject("add1", add1);
        mv.addObject("city", city);
        mv.addObject("add2", add2);
        mv.addObject("zipcode", zipcode);
        mv.addObject("state", state);
        return mv;
    }

    @RequestMapping("/profile/update")
    public String updateInfo(
            @RequestParam("fullname") String fullname,
            @RequestParam("birthdate") String birthdateString,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("add1") String add1,
            @RequestParam("add2") String add2,
            @RequestParam("zipcode") String zipcode,
            @RequestParam("state") String state,
            @RequestParam("city") String city,
            HttpSession session) throws ParseException {
        Date birthdate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = sdf.parse(birthdateString);
        birthdate = new java.sql.Date(parsedDate.getTime());

        int userid = (int) session.getAttribute("userid");
        String sql = "UPDATE user SET fullname=?, birthdate=?, password=?, phone=?, add1=?, add2=?, zipcode=?, state=?, city=? WHERE id=?";
        System.out.println("updating for" + userid);
        template.update(sql, fullname, birthdate, password, phone, add1, add2, zipcode, state,city, userid);
        return "redirect:/profile";
    }

}
