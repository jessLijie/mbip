package my.utm.ip.spring_jdbc.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import my.utm.ip.spring_jdbc.model.MonthlyCarbonFootprint;
import my.utm.ip.spring_jdbc.model.PercentageContributions;

@Controller
public class adminDashboardController {
    @Autowired
    JdbcTemplate template;

    @RequestMapping("/adminDashboard")
    public ModelAndView dashboard(HttpSession session){
        ModelAndView mv = new ModelAndView("/Admin/adminDashboard");
        
        int userid = (int) session.getAttribute("userid");
        mv.addObject("userid",userid);

        // retrieve username
        String sql = "Select username from user where id=?";
        List<Map<String, Object>> nameResult = template.queryForList(sql, userid);
        Map<String, Object> user = nameResult.get(0);
        String name = (String) user.get("username");

        
        //retrieve total recycle weight
        String recycleSql= "Select SUM(currentConsumption) as totalWeight from recycle";
        Map<String, Object> recycleResult= template.queryForMap(recycleSql);

        BigDecimal currentConsumption= (BigDecimal)recycleResult.get("totalWeight"); 

        String formattedTotalRecycle= String.format("%.2f", currentConsumption.doubleValue());


        //retrieve total electricity
        String electricitySql= "Select SUM(currentConsumption) as totalElectricity from electricity";
        Map<String, Object> electricityResult= template.queryForMap(electricitySql);
        
        BigDecimal currentElectricity= (BigDecimal)electricityResult.get("totalElectricity"); 
        
        String formattedTotalElectricity= String.format("%.2f", currentElectricity.doubleValue());


        //retrieve total water
        String waterSql= "Select SUM(currentConsumption) as totalWater from water";
        Map<String, Object> waterResult= template.queryForMap(waterSql);
        
        BigDecimal currentWater= (BigDecimal)waterResult.get("totalWater"); 
        
        String formattedTotalWater= String.format("%.2f", currentWater.doubleValue());


        // retrieve total carbon footprint
        String totalCarbonSql= "SELECT SUM(e.carbonFootprint) + SUM(w.carbonFootprint) AS totalCarbonFootprint " + 
                                "FROM electricity e JOIN water w " ; 
       
        Map<String, Object> totalCarbonResult= template.queryForMap(totalCarbonSql);
                        
        BigDecimal totalCarbon= (BigDecimal)totalCarbonResult.get("totalCarbonFootprint");
        String formattedTotalCarbon= String.format("%.2f", totalCarbon.doubleValue());
    


        // retrieve data for carbon footprint linechart (carbon footprint vs month)
        String totalCarbonFootprintByMonthSql = "SELECT e.month, SUM(e.carbonFootprint) + SUM(w.carbonFootprint) AS totalCarbonFootprint " +
                                                "FROM electricity e JOIN water w " +
                                                "ON e.userid = w.userid AND e.month = w.month " +
                                                "GROUP BY e.month ORDER BY e.month";

        List<Map<String, Object>> carbonFootprintByMonth = template.queryForList(totalCarbonFootprintByMonthSql);
        List<MonthlyCarbonFootprint> carbonDataList = new ArrayList<>();

        for (Map<String, Object> carbonFootprint : carbonFootprintByMonth) {
            int month = (int) carbonFootprint.get("month");
            BigDecimal totalCarbonFootprint = (BigDecimal) carbonFootprint.get("totalCarbonFootprint");
            double formattedTotalCarbonFootprint = (totalCarbonFootprint != null) ? totalCarbonFootprint.doubleValue() : 0.0;
            String formattedTotalCarbonFootprintString = String.format("%.2f", formattedTotalCarbonFootprint);

            MonthlyCarbonFootprint monthlyCarbonData = new MonthlyCarbonFootprint(month, formattedTotalCarbonFootprintString);
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


        // retrieve data for doughnut chart (percentage contribution to total carbon footprint)
        String carbonFootprintPercentageSql=  "SELECT " + 
                                              "(SUM(e.carbonFootprint) / (SUM(e.carbonFootprint) + SUM(w.carbonFootprint))) * 100 AS electricityPercentage, " + 
                                              "(SUM(w.carbonFootprint) / (SUM(e.carbonFootprint) + SUM(w.carbonFootprint))) * 100 AS waterPercentage " +
                                              "FROM electricity e JOIN water w " + 
                                              "on e.userid = w.userid AND e.month = w.month " ;

        List<Map<String, Object>> percentageContribution= template.queryForList(carbonFootprintPercentageSql);
        List<PercentageContributions> contributionList= new ArrayList<>(); 
        
        for(Map<String, Object> percentages : percentageContribution){
            BigDecimal electricityPercentage= (BigDecimal)percentages.get("electricityPercentage");
            BigDecimal waterPercentage= (BigDecimal)percentages.get("waterPercentage");

            // Round the percentages to 1 decimal point
            electricityPercentage = electricityPercentage.setScale(1, RoundingMode.HALF_UP);
            waterPercentage = waterPercentage.setScale(1, RoundingMode.HALF_UP);

            PercentageContributions contribution= new PercentageContributions(electricityPercentage, waterPercentage);
            contributionList.add(contribution);
        }

        objectMapper.registerModule(new SimpleModule().addSerializer(PercentageContributions.class, new PercentageContributionsSerializer()));
        
        String contributionListJson;

        try {
            contributionListJson = objectMapper.writeValueAsString(contributionList);
            System.out.println("Contribution List JSON: " + contributionListJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            contributionListJson = "";
        }

        mv.addObject("name", name);
        mv.addObject("userid", userid);
        mv.addObject("recycleWeight", formattedTotalRecycle);
        mv.addObject("totalElectricity", formattedTotalElectricity);
        mv.addObject("totalWater", formattedTotalWater);
        mv.addObject("totalCarbonFootprint", formattedTotalCarbon);
        mv.addObject("carbonDataListJson", carbonDataListJson);
        mv.addObject("contributionList", contributionListJson);



   

        return mv;
    } 
}
