package my.utm.ip.spring_jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import my.utm.ip.spring_jdbc.model.Recycle;
import my.utm.ip.spring_jdbc.model.User;

import java.util.logging.Logger;
import java.util.List;

@RestController
public class RecycleController {

    @Autowired
    JdbcTemplate template;

    @RequestMapping("/RecycleHistory")
    public ModelAndView mainpage() {
        ModelAndView mv = new ModelAndView("/Recycle/RecycleHistory");
        mv.addObject("title", "List Records");

        String sql = "SELECT * FROM recycle WHERE userid=1";

        List<Recycle> recycleList = template.query(sql, new BeanPropertyRowMapper<>(Recycle.class));

        mv.addObject("recycleList", recycleList);
        String userSql = "SELECT * FROM user WHERE userid=1";
        List<User> userList = template.query(userSql, new BeanPropertyRowMapper<>(User.class));
        if (!userList.isEmpty()) {
            mv.addObject("user", userList.get(0));
        }

        return mv;
    }


    
    private static final Logger LOGGER = Logger.getLogger(RecycleController.class.getName());

    @RequestMapping("/applyFilter")
    public ModelAndView applyFilter(@RequestParam("year") int year, @RequestParam("month") String month) {

        ModelAndView mv = new ModelAndView("/Recycle/RecycleHistory");
        String[] selectedMonths = month.split(",");
        mv.getModel().remove("recycleList");
        StringBuilder sql = new StringBuilder("SELECT * FROM recycle WHERE userid = 1 ");
        sql.append("AND year =  ");
        sql.append(year);
        sql.append(" AND month IN (");
        for (int i = 0; i < selectedMonths.length; i++) {
            if (i > 0) {
                sql.append(", ");
                sql.append(selectedMonths[i]);
            }
            else{
            sql.append(selectedMonths[i]);
            }
        }
        sql.append(")");
        LOGGER.info("hey"+sql.toString());
        List<Recycle> recycleList = template.query(sql.toString(), new BeanPropertyRowMapper<>(Recycle.class));
        mv.addObject("recycleList", recycleList);

        return mv;
    }
}
