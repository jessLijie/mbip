package my.utm.ip.spring_jdbc.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import my.utm.ip.spring_jdbc.model.Recycle;
import my.utm.ip.spring_jdbc.model.User;
@Controller
public class adminController {
     @Autowired
    JdbcTemplate template;
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
