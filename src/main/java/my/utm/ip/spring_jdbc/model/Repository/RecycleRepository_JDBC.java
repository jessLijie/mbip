

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
import my.utm.ip.spring_jdbc.model.DAO.RecycleDAO;
import my.utm.ip.spring_jdbc.model.DAO.UserDAO;

@Repository
public class RecycleRepository_JDBC  implements RecycleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RecycleDAO> getRecycleByUserId(int userId) {
        String sql = "SELECT * FROM recycle WHERE userid=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RecycleDAO.class), userId);
    }

    @Override
    public List<RecycleDAO> filterRecycle(int userId, int year, String[] selectedMonths) {
        if (selectedMonths == null || selectedMonths.length == 0) {
            throw new IllegalArgumentException("Selected months must not be null or empty");
        }

        String sql = "SELECT * FROM recycle WHERE userid = ? AND year = ? AND month IN ("
                + String.join(",", Collections.nCopies(selectedMonths.length, "?")) + ")";

        List<Object> params = new ArrayList<>();
        params.add(userId);
        params.add(year);
        params.addAll(Arrays.asList(selectedMonths));

        return jdbcTemplate.query(sql, params.toArray(), new BeanPropertyRowMapper<>(RecycleDAO.class));
    }

    @Override
    public RecycleDAO getRecycleById(int billId) {
        String sql = "SELECT id, address, month, year, currentConsumption, carbonFootprint FROM recycle WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(RecycleDAO.class), billId);
    }

    @Override
    public Integer getMaxRecycleId() {
        String sql = "SELECT MAX(id) FROM recycle";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void insertRecycle(RecycleDAO recycle) {
        String sql = "INSERT INTO recycle (id, userid, address, year, month, currentConsumption, carbonFootprint, bill_img) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, recycle.getId(), recycle.getUserid(), recycle.getAddress(),
                    recycle.getYear(), recycle.getMonth(), recycle.getCurrentConsumption(),
                    recycle.getCarbonFootprint(), recycle.getBillImg());
        } catch (DataAccessException e) {
            // Handle exception or log it
            e.printStackTrace();
            throw new RuntimeException("Error inserting recycle record", e);
        }
    }

    @Override
    public UserDAO getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(UserDAO.class));
    }

   

}
