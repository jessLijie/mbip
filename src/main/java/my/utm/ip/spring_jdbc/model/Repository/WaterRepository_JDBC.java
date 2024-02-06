package my.utm.ip.spring_jdbc.model.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import my.utm.ip.spring_jdbc.model.DAO.WaterDAO;
import my.utm.ip.spring_jdbc.model.DAO.UserDAO;

@Repository
public class WaterRepository_JDBC implements WaterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<WaterDAO> getWaterByUserId(int userId) {
        String sql = "SELECT * FROM water WHERE userid=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WaterDAO.class), userId);
    }

    @Override
    public List<WaterDAO> filterWater(int userId, int year, String[] selectedMonths) {
        if (selectedMonths == null || selectedMonths.length == 0) {
            throw new IllegalArgumentException("Selected months must not be null or empty");
        }

        String sql = "SELECT * FROM water WHERE userid = ? AND year = ? AND month IN ("
                + String.join(",", Collections.nCopies(selectedMonths.length, "?")) + ")";

        List<Object> params = new ArrayList<>();
        params.add(userId);
        params.add(year);
        params.addAll(Arrays.asList(selectedMonths));

        return jdbcTemplate.query(sql, params.toArray(), new BeanPropertyRowMapper<>(WaterDAO.class));
    }

    @Override
    public WaterDAO getWaterById(int billId) {
        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint FROM water WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(WaterDAO.class), billId);
    }

    @Override
    public Integer getMaxWaterId() {
        String sql = "SELECT MAX(id) FROM water";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void insertWater(WaterDAO water) {
        String sql = "INSERT INTO water (id, userid, address, year, month, currentConsumption, carbonFootprint, bill_img) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, water.getId(), water.getUserid(), water.getAddress(),
                water.getYear(), water.getMonth(), water.getCurrentConsumption(),
                water.getCarbonFootprint(), water.getBillImg());
        } catch (DataAccessException e) {
            // Handle exception or log it
            e.printStackTrace();
            throw new RuntimeException("Error inserting water record", e);
        }
    }

    @Override
    public UserDAO getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(UserDAO.class));
    }

}
