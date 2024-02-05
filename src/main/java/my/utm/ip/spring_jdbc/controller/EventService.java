package my.utm.ip.spring_jdbc.controller;

import my.utm.ip.spring_jdbc.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Event getEventById(int id) {
        String sql = "SELECT * FROM event WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Event.class));
    }
}
