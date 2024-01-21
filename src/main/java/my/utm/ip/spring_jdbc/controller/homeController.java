package my.utm.ip.spring_jdbc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mbip")
public class homeController {
    @Autowired
    JdbcTemplate template;

    @RequestMapping({ "/" })
    public String greet() {
        return "welcome";
    }

    @RequestMapping({ "/login" })
    public String login(@RequestParam("username") String un,
            @RequestParam("password") String pass,
            Model model,
            HttpSession session)
    {

        String sql = "Select id, username, password, role from user where username=? ";
        List<Map<String, Object>> result = template.queryForList(sql, un);
        
        if (!result.isEmpty()) {
            Map<String, Object> user = result.get(0);
            String storedPassword = (String) user.get("password");
            String role = (String) user.get("role");


            if (pass.equals(storedPassword)) {
                int userid = (int) user.get("id");
                session.setAttribute("userid", userid);
                session.setAttribute("role", role);
                if(role.equals("user")){
                return "redirect:/userDashboard";
                }

                else {

                    return "redirect:/adminDashboard";
                }
                
            }
        }

        model.addAttribute("error", "Invalid username or password");
        return "welcome";
    }

    @RequestMapping({ "/logout" })
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "welcome";
    }

}
