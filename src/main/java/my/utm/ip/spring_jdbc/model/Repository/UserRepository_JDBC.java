package my.utm.ip.spring_jdbc.model.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import my.utm.ip.spring_jdbc.model.DAO.UserDAO;

@Repository
public class UserRepository_JDBC implements UserRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;
    public UserDAO getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        System.out.println("Executing SQL: " + sql + " with parameter: " + id);
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(UserDAO.class));
    }
    
}
