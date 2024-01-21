package my.utm.ip.spring_jdbc.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class userProfileController {
    @Autowired
    JdbcTemplate template;
    
    @RequestMapping("/profile")
    public ModelAndView profile(HttpSession session){
        ModelAndView mv = new ModelAndView("/Profile/userProfile");
        int userid = (int) session.getAttribute("userid");
        mv.addObject("userid",userid);

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
        






        return mv;
    }

    @RequestMapping("/profile/edit")
    public ModelAndView profileEdit(){
        ModelAndView mv = new ModelAndView("/Profile/userProfileEdit");
        return mv;
    }


    
}
