package my.utm.ip.spring_jdbc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

        String sql = "Select id, username, password from user where username=? ";
        List<Map<String, Object>> result = template.queryForList(sql, un);
        
        if (!result.isEmpty()) {
            Map<String, Object> user = result.get(0);
            String storedPassword = (String) user.get("password");

            if (pass.equals(storedPassword)) {
                // HttpSession session = request.getSession();
                // int userid = 1;
                int userid = (int) user.get("id");
                session.setAttribute("userid", userid);
                return "redirect:/userDashboard";
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
