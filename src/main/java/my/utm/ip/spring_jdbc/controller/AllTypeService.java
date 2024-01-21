package my.utm.ip.spring_jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AllTypeService {

    @Autowired
    private JdbcTemplate template;

    public double cumulativeConsumption(int userId, String consumptionType) {
        String sql = "";
        if ("water".equalsIgnoreCase(consumptionType)) {
            sql = "SELECT SUM(currentConsumption) FROM water WHERE userid = ?";
        } else if ("electricity".equalsIgnoreCase(consumptionType)) {
            sql = "SELECT SUM(currentConsumption) FROM electricity WHERE userid = ?";
        } else if ("recycle".equalsIgnoreCase(consumptionType)) {
            sql = "SELECT SUM(currentConsumption) FROM recycle WHERE userid = ?";
        }

        double result = template.queryForObject(sql, new Object[]{userId}, Double.class);

        return result != 0 ? result : 0.0;
    }

    public double cumulativeCarbonFootprint(int userId, String consumptionType) {
        String sql = "";
        if ("water".equalsIgnoreCase(consumptionType)) {
            sql = "SELECT SUM(carbonFootprint) FROM water WHERE userid = ?";
        } else if ("electricity".equalsIgnoreCase(consumptionType)) {
            sql = "SELECT SUM(carbonFootprint) FROM electricity WHERE userid = ?";
        } else if ("recycle".equalsIgnoreCase(consumptionType)) {
            sql = "SELECT SUM(carbonFootprint) FROM recycle WHERE userid = ?";
        }

        double result = template.queryForObject(sql, new Object[]{userId}, Double.class);

        return result != 0 ? result : 0.0;
    }
}
