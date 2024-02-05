package my.utm.ip.spring_jdbc.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import my.utm.ip.spring_jdbc.model.Electricity;
import my.utm.ip.spring_jdbc.model.User;


@Controller
@RequestMapping("/electricity")
public class ElectricityController {

    @Autowired
    JdbcTemplate template;

    @Autowired
    private UserService userService;

    @RequestMapping({ "/ElectricityHistory" })
    public ModelAndView historypage(HttpSession session) {
        ModelAndView mv = new ModelAndView("/Electricity/ElectricityHistory");
        mv.addObject("title", "List Records");

        int userid= (int)session.getAttribute("userid");
        String sql = "SELECT * FROM electricity WHERE userid=" + userid;

        List<Electricity> electricityList = template.query(sql, new BeanPropertyRowMapper<>(Electricity.class));

        mv.addObject("electricityList", electricityList);
        String userSql = "SELECT * FROM user WHERE id=" + userid;
        List<User> userList = template.query(userSql, new BeanPropertyRowMapper<>(User.class));
        if (!userList.isEmpty()) {
            mv.addObject("user", userList.get(0));
        }

        return mv;
    }

    private static final Logger LOGGER = Logger.getLogger(ElectricityController.class.getName());

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
        List<Electricity> recycleList = template.query(sql.toString(), new BeanPropertyRowMapper<>(Electricity.class));
        mv.addObject("recycleList", recycleList);

        return mv;
    }

    @RequestMapping("/ElectricityHistoryView")
    public ModelAndView historyDetail(@RequestParam("billId") int billId) {
        ModelAndView modelAndView = new ModelAndView("/Electricity/ViewDetailHistory");
        String sql = "SELECT id, address, date, currentConsumption FROM electricity WHERE id = ?";

        Electricity electricityBill = template.queryForObject(sql, new Object[] { billId }, (resultSet, rowNum) -> {
            Electricity bill = new Electricity();
            // Use 'id' to identify the bill
            bill.setId(resultSet.getInt("id"));
            bill.setAddress(resultSet.getString("address"));
            bill.setDate(resultSet.getString("date"));
            bill.setCurrentConsumption(resultSet.getDouble("currentConsumption"));
            return bill;
        });

        // Assuming prorataFactor is always 1.00000
        double prorataFactor = 1.00000;

        double carbonFootprint = electricityBill.getCurrentConsumption() * 0.584 * prorataFactor;
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        carbonFootprint = Double.parseDouble(decimalFormat.format(carbonFootprint));
        modelAndView.addObject("electricityBill", electricityBill);
        modelAndView.addObject("carbonFootprint", carbonFootprint);

        return modelAndView;
    }

    @RequestMapping("/ElectricityDownloadReport")
    public ModelAndView downloadReport(
            @RequestParam("billId") int billId, HttpSession session) {

        ModelAndView modelAndView = new ModelAndView("/Electricity/downloadreport");
        int userid = 1; // Replace this with your actual authentication logic
        session.setAttribute("userid", userid);
        User user = userService.getUserById(userid);

        String sql = "SELECT id, address, date, currentConsumption FROM electricity WHERE id = ?";

        double prorataFactor = 1.00000;

        Electricity electricityBill = template.queryForObject(sql, new Object[] { billId }, (resultSet, rowNum) -> {
            Electricity bill = new Electricity();
            // Use 'id' to identify the bill
            bill.setId(resultSet.getInt("id"));
            bill.setAddress(resultSet.getString("address"));
            bill.setDate(resultSet.getString("date"));
            bill.setCurrentConsumption(resultSet.getDouble("currentConsumption"));
            return bill;
        });

        double carbonFootprint = electricityBill.getCurrentConsumption() * 0.584 * prorataFactor;
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        carbonFootprint = Double.parseDouble(decimalFormat.format(carbonFootprint));
        modelAndView.addObject("electricityBill", electricityBill);
        modelAndView.addObject("carbonFootprint", carbonFootprint);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping({ "/ElectricityFootprint" })
    public String footpring() {
        return "/Electricity/ElectricityFootprint";
    }

    @RequestMapping({ "/InsertElectricityConsumption" })
    public String insert() {
        return "/Electricity/InsertElectricityConsumption";
    }


}
