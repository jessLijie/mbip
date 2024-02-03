package my.utm.ip.spring_jdbc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import my.utm.ip.spring_jdbc.model.MonthlyCarbonFootprint;


@Controller
public class userDashboardController {
   @Autowired
    JdbcTemplate template;
    @RequestMapping("/userDashboard")
    public ModelAndView dashboard(HttpSession session){
        ModelAndView mv = new ModelAndView("/Dashboard/UserDashboard");
        
        int userid = (int) session.getAttribute("userid");
        mv.addObject("userid",userid);

        // retrieve username
        String sql = "Select username from user where id=?";
        List<Map<String, Object>> nameResult = template.queryForList(sql, userid);
        Map<String, Object> user = nameResult.get(0);
        String name = (String) user.get("username");

        
        //retrieve total recycle weight
        String recycleSql= "Select currentConsumption from recycle where userid=?";
        List<Map<String, Object>> recycleResult= template.queryForList(recycleSql, userid);

        Double totalWeight= 0.0;

        for(Map<String, Object> recycle: recycleResult){
            BigDecimal currentConsumption= (BigDecimal)recycle.get("currentConsumption");
            totalWeight += currentConsumption.doubleValue();
        }


        //retrieve total electricity consumption 
        String electricitySql= "Select currentConsumption from electricity where userid=?";
        List<Map<String, Object>> electricityResult= template.queryForList(electricitySql, userid);

        Double totalElectricity= 0.0;

        for(Map<String, Object> electrcity: electricityResult){
            BigDecimal currentConsumption= (BigDecimal)electrcity.get("currentConsumption");
            totalElectricity += currentConsumption.doubleValue();
        }

        String formattedTotalElectricity = String.format("%.2f", totalElectricity);


        //retrieve total water consumption 
        String waterSql= "Select currentConsumption from water where userid=?";
        List<Map<String, Object>> waterResult= template.queryForList(waterSql, userid);

        Double totalWater= 0.0;

        for(Map<String, Object> water: waterResult){
            BigDecimal currentConsumption= (BigDecimal)water.get("currentConsumption");
            totalWater += currentConsumption.doubleValue();
        }

        String formattedTotalWater = String.format("%.2f", totalWater);
        

        //retrieve total carbon comsumption 
        String totalCarbonSql= "SELECT SUM(e.carbonFootprint) + SUM(w.carbonFootprint) AS totalCarbonFootprint " + 
                                "FROM electricity e JOIN water w " + 
                                "ON e.userid= w.userid " +
                                "WHERE e.userid=?";

        List<Map<String, Object>> totalCarbonResult= template.queryForList(totalCarbonSql, userid);
        String formattedTotalCarbon= "";

        if(!totalCarbonResult.isEmpty()){
            BigDecimal totalCarbon= (BigDecimal)totalCarbonResult.get(0).get("totalCarbonFootprint");
            formattedTotalCarbon= String.format("%.2f", totalCarbon.doubleValue());
        }else{
            formattedTotalCarbon= "0";
        }


        // retrieve data for carbon footprint linechart
        String totalCarbonFootprintByMonthSql=  "SELECT e.month, SUM(e.carbonFootprint) + SUM(w.carbonFootprint) AS totalCarbonFootprint " + 
                                                "FROM electricity e JOIN water w " + 
                                                "on e.userid = w.userid AND e.month = w.month " + 
                                                "WHERE e.userid =? " +
                                                "GROUP BY e.month ORDER BY e.month";
        
        List<Map<String, Object>> carbonFootprintByMonth= template.queryForList(totalCarbonFootprintByMonthSql, userid);
        List<MonthlyCarbonFootprint> carbonDataList= new ArrayList<>();

        for(Map<String, Object> carbonFootprint : carbonFootprintByMonth){
            int month= (int)carbonFootprint.get("month");
            BigDecimal carbonData= (BigDecimal)carbonFootprint.get("totalCarbonFootprint");
            String monthlyCarbonFootprint= String.format("%.2f", carbonData.doubleValue());

            MonthlyCarbonFootprint monthlyCarbonData= new MonthlyCarbonFootprint(month, monthlyCarbonFootprint);
            carbonDataList.add(monthlyCarbonData);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new SimpleModule());
        objectMapper.registerModule(new SimpleModule().addSerializer(MonthlyCarbonFootprint.class, new MonthlyCarbonFootprintSerializer()));

        String carbonDataListJson;
        
        try {
            carbonDataListJson = objectMapper.writeValueAsString(carbonDataList);
            System.out.println("Carbon Data List JSON: " + carbonDataListJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            carbonDataListJson = "";
        }
        
        mv.addObject("name", name);
        mv.addObject("userid", userid);
        mv.addObject("recycleWeight", totalWeight);
        mv.addObject("totalElectricity", formattedTotalElectricity);
        mv.addObject("totalWater", formattedTotalWater); 
        mv.addObject("totalCarbon", formattedTotalCarbon); 
        mv.addObject("carbonDataListJson", carbonDataListJson);

        return mv;
    }
}
