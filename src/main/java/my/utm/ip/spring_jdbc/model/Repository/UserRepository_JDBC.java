package my.utm.ip.spring_jdbc.model.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import my.utm.ip.spring_jdbc.model.DAO.UserDAO;

public class UserRepository_JDBC {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public UserDAO getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(UserDAO.class));
    }
}
