package my.utm.ip.spring_jdbc.controller;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class userDashboardController {
   @Autowired
    JdbcTemplate template;
    @RequestMapping("/userDashboard")
    public ModelAndView dashboard(HttpSession session){
        ModelAndView mv = new ModelAndView("/Dashboard/UserDashboard");
        
        int userid = (int) session.getAttribute("userid");
        mv.addObject("userid",userid);
        System.out.println(userid);

        String sql = "Select username from user where id=?";
        List<Map<String, Object>> result = template.queryForList(sql, userid);
        Map<String, Object> user = result.get(0);
        String name = (String) user.get("username");
        System.out.println(name);
        mv.addObject("name", name);
        mv.addObject("userid", userid);
        return mv;
    }
}
