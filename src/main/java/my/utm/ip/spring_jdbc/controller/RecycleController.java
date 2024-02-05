package my.utm.ip.spring_jdbc.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.jdbc.Blob;

import my.utm.ip.spring_jdbc.model.User;
import my.utm.ip.spring_jdbc.model.Recycle;
import my.utm.ip.spring_jdbc.model.Recycle;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/recycle")
public class RecycleController {
    @Autowired
    JdbcTemplate template;

    @Autowired
    private UserService userService;

    @Autowired
    private AllTypeService allTypeService;

    @RequestMapping("/RecycleHistory" )
    public ModelAndView historypage(HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView mv = new ModelAndView("/Recycle/RecycleHistory");
        mv.addObject("title", "List Records");

        String sql = "SELECT * FROM recycle WHERE userid=" + userid;

        List<Recycle> recycleList = template.query(sql, new BeanPropertyRowMapper<>(Recycle.class));
        mv.addObject("recycleList", recycleList);

        String userSql = "SELECT * FROM user WHERE id=" + userid;
        List<User> userList = template.query(userSql, new BeanPropertyRowMapper<>(User.class));
        if (!userList.isEmpty()) {
            mv.addObject("user", userList.get(0));
        }

        return mv;
    }

    @RequestMapping("/applyFilter")
    public ModelAndView applyFilter(@RequestParam("year") int year, @RequestParam("month") String month,
            HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView mv = new ModelAndView("/Recycle/RecycleHistory");
        String[] selectedMonths = month.split(",");
        mv.getModel().remove("recycleList");
        StringBuilder sql = new StringBuilder("SELECT * FROM recycle WHERE userid=" + userid);
        sql.append("AND year =  ");
        sql.append(year);
        sql.append(" AND month IN (");
        for (int i = 0; i < selectedMonths.length; i++) {
            if (i > 0) {
                sql.append(", ");
                sql.append(selectedMonths[i]);
            } else {
                sql.append(selectedMonths[i]);
            }
        }
        sql.append(")");
        List<Recycle> recycleList = template.query(sql.toString(), new BeanPropertyRowMapper<>(Recycle.class));
        mv.addObject("recycleList", recycleList);

        return mv;
    }

    @RequestMapping("/RecycleHistoryDetail")
    public ModelAndView historyDetail(@RequestParam("billId") int billId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Recycle/RecycleHistoryDetail");

        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint FROM recycle WHERE id=?";

        Recycle recycleBill = template.queryForObject(sql, new Object[] { billId },
                new BeanPropertyRowMapper<>(Recycle.class));

        String period = Recycle.getPeriod(recycleBill.getMonth(), recycleBill.getYear());

        modelAndView.addObject("recycleBill", recycleBill);
        modelAndView.addObject("period", period);

        return modelAndView;
    }

    @RequestMapping("/RecycleDownloadReport")
    public ModelAndView downloadReport(@RequestParam("billId") int billId, HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Recycle/RecycleDownloadReport");
        User user = userService.getUserById(userid);

        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint FROM recycle WHERE id=?";
        Recycle recycleBill = template.queryForObject(sql, new Object[] { billId },
                new BeanPropertyRowMapper<>(Recycle.class));

        String period = Recycle.getPeriod(recycleBill.getMonth(), recycleBill.getYear());

        modelAndView.addObject("recycleBill", recycleBill);
        modelAndView.addObject("period", period);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping("/Recycle")
    public ModelAndView mainpage(HttpSession session) {
        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);
        ModelAndView modelAndView = new ModelAndView("/Recycle/InsertRecycleConsumption");
        User user = userService.getUserById(userid);

        modelAndView.addObject("user", user);

        return modelAndView;
    }
    




    @RequestMapping("/InsertRecycleConsumptionDAta")
    public String insert(@RequestParam("address1") String address1,
            @RequestParam("address2") String address2,
            @RequestParam("postcode") String postcode,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("period") String period,
            @RequestParam("totalWConsumption") double totalWConsumption,
            @RequestParam("bill_img") MultipartFile bill_img,
            HttpServletRequest request, HttpSession session) throws IOException{

        int userid = (int) session.getAttribute("userid");
        session.setAttribute("userid", userid);

        String fullAddress = address1 + "<br>" + address2 + "<br>" + postcode + ", " + city + "<br>" + state;

        String inputdate = period;
        String split_values[] = inputdate.split("-");
        int year = Integer.parseInt(split_values[0].trim());
        int month = Integer.parseInt(split_values[1].trim());

        String lastRecycleIdSql = "SELECT MAX(id) FROM recycle";
        Integer lastRecycleId = template.queryForObject(lastRecycleIdSql, Integer.class);
        int billId = lastRecycleId != null ? lastRecycleId + 1 : 1;

        double carbonFootprint = totalWConsumption * 2.860;

        Recycle recycle = new Recycle();
        recycle.setUserid(userid);
        recycle.setId(billId);
        recycle.setAddress(fullAddress);
        recycle.setYear(year);
        recycle.setMonth(month);
        recycle.setCurrentConsumption(totalWConsumption);
        recycle.setCarbonFootprint(carbonFootprint);
        if (!bill_img.isEmpty()) {
            byte[] fileBytes = bill_img.getBytes();
            recycle.setBillImg(fileBytes);
        }

        String sql = "INSERT INTO recycle (id, userid, address, year, month, currentConsumption, carbonFootprint, bill_img) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, recycle.getId(), recycle.getUserid(), recycle.getAddress(), recycle.getYear(),
                recycle.getMonth(), recycle.getCurrentConsumption(), recycle.getCarbonFootprint(),
                recycle.getBillImg());

        return "redirect:/recycle/RecycleFootprint?billId=" + recycle.getId();
    }

    @RequestMapping({ "/RecycleFootprint" })
    public ModelAndView footprint(@RequestParam("billId") int billId, HttpSession session) {

        int userid = (int) session.getAttribute("userid");
        ModelAndView modelAndView = new ModelAndView("/Recycle/RecycleFootprint");

        session.setAttribute("userid", userid);
        // User user = userService.getUserById(userid);

        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint FROM Recycle WHERE id=?";
        Recycle recycleBill = template.queryForObject(sql, new Object[] { billId },
                new BeanPropertyRowMapper<>(Recycle.class));

        String period = Recycle.getPeriod(recycleBill.getMonth(), recycleBill.getYear());

        double sumConsumption = allTypeService.cumulativeConsumption(userid, "Recycle");
        double sumCarbonFootprint = allTypeService.cumulativeCarbonFootprint(userid, "Recycle");

        modelAndView.addObject("recycleBill", recycleBill);
        modelAndView.addObject("period", period);
        modelAndView.addObject("sumConsumption", sumConsumption);
        modelAndView.addObject("sumCarbonFootprint", sumCarbonFootprint);

        return modelAndView;
    }

    @RequestMapping({ "/RecycleUpdateBill" })
public ModelAndView updatebill(@RequestParam("billId") int billId, HttpSession session) {
    int userid = (int) session.getAttribute("userid");
    ModelAndView modelAndView = new ModelAndView("/Recycle/Editbill");

    String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint, bill_img FROM recycle WHERE id=?";

    List<Recycle> result = template.query(sql, new Object[]{billId}, new BeanPropertyRowMapper<>(Recycle.class));

    if (!result.isEmpty()) {
        Recycle recycleBill = result.get(0);

        String period = Recycle.getPeriod(recycleBill.getMonth(), recycleBill.getYear());
        User user = userService.getUserById(userid);
        modelAndView.addObject("user", user);
        modelAndView.addObject("recycleBill", recycleBill);
        modelAndView.addObject("period", period);
        
        byte[] imageData = recycleBill.getBillImg();
        if (imageData != null) {

            modelAndView.addObject("billimg", imageData);
            modelAndView.addObject("hey", "hey");
        } else {
            modelAndView.addObject("hey", "yo");
            modelAndView.addObject("billimg", ""); // Set an empty string or some default value
        }
    } else {
        // Handle the case where no record is found for the specified billId
        // You may want to show an error message or redirect to an error page
        modelAndView.addObject("errorMessage", "No record found for the specified billId");
    }

    return modelAndView;
}

// Create a ResultSetExtractor class to handle the BLOB data retrieval
private class RecycleResultSetExtractor implements ResultSetExtractor<List<Recycle>> {
    @Override
    public List<Recycle> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Recycle> result = new ArrayList<>();
        while (rs.next()) {
            Recycle recycle = new Recycle();
            recycle.setId(rs.getInt("id"));
            recycle.setAddress(rs.getString("address"));
            recycle.setMonth(rs.getInt("month"));
            recycle.setYear(rs.getInt("year"));
            recycle.setCurrentConsumption(rs.getDouble("currentConsumption"));
            recycle.setCarbonFootprint(rs.getDouble("carbonFootprint"));
            
            Blob blob = (Blob) rs.getBlob("bill_img");
            if (blob != null) {
                recycle.setBillImg(blob.getBytes(1, (int) blob.length()));
            }
            
            result.add(recycle);
        }
        return result;
    }
}
}