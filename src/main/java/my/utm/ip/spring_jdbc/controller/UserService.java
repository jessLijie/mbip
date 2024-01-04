package my.utm.ip.spring_jdbc.controller;

import my.utm.ip.spring_jdbc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUserById(int userId) {
        String sql = "SELECT * FROM user WHERE userid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, (resultSet, rowNum) -> {
            User user = new User();
            user.setId(resultSet.getInt("userid"));
            user.setFullname(resultSet.getString("fullname"));
            user.setEmail(resultSet.getString("email"));
            user.setPhone(resultSet.getString("phone"));
            user.setAdd1(resultSet.getString("address"));
            // Set other user properties based on your database schema
            return user;
        });
    }
}
