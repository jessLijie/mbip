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
import my.utm.ip.spring_jdbc.model.DAO.ElectricityDAO;
import my.utm.ip.spring_jdbc.model.DAO.UserDAO;

@Repository
public class ElectricityRepository_JDBC implements ElectricityRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ElectricityDAO> getElectricityByUserId(int userId) {
        String sql = "SELECT * FROM electricity WHERE userid=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ElectricityDAO.class), userId);
    }

    @Override
    public List<ElectricityDAO> filterElectricity(int userId, int year, String[] selectedMonths) {
        if (selectedMonths == null || selectedMonths.length == 0) {
            throw new IllegalArgumentException("Selected months must not be null or empty");
        }

        String sql = "SELECT * FROM electricity WHERE userid = ? AND year = ? AND month IN ("
                + String.join(",", Collections.nCopies(selectedMonths.length, "?")) + ")";

        List<Object> params = new ArrayList<>();
        params.add(userId);
        params.add(year);
        params.addAll(Arrays.asList(selectedMonths));

        return jdbcTemplate.query(sql, params.toArray(), new BeanPropertyRowMapper<>(ElectricityDAO.class));
    }

    @Override
    public ElectricityDAO getElectricityById(int billId) {
        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint FROM electricity WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ElectricityDAO.class), billId);
    }

    @Override
    public Integer getMaxElectricityId() {
        String sql = "SELECT MAX(id) FROM electricity";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void insertElectricity(ElectricityDAO electricity) {
        String sql = "INSERT INTO electricity (id, userid, address, year, month, currentConsumption, carbonFootprint, bill_img) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, electricity.getId(), electricity.getUserid(), electricity.getAddress(),
                    electricity.getYear(), electricity.getMonth(), electricity.getCurrentConsumption(),
                    electricity.getCarbonFootprint(), electricity.getBillImg());
        } catch (DataAccessException e) {
            // Handle exception or log it
            e.printStackTrace();
            throw new RuntimeException("Error inserting electricity record", e);
        }
    }

    @Override
    public UserDAO getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(UserDAO.class));
    }

   

}
