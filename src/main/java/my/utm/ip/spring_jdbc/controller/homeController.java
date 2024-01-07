package my.utm.ip.spring_jdbc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mbip")
public class homeController {
   
    @RequestMapping({"/hello"})
    public String greet(){
        return "welcome";
    }

    
    @RequestMapping({"/login"})
    public String login(HttpServletRequest request) {
    // Authenticate the user and obtain the userid
    int userid = 1;  // Replace this with your actual authentication logic

    // Get or create the session
    HttpSession session = request.getSession();

    // Set userid in session
    session.setAttribute("userid", userid);

    // Redirect to some other page after successful login
    return "redirect:/welcome";
}
}
