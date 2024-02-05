package my.utm.ip.spring_jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class Config {
    private DriverManagerDataSource dataSource() {
 DriverManagerDataSource source = 
 new DriverManagerDataSource();
 source.setDriverClassName("com.mysql.cj.jdbc.Driver");
 source.setUrl("jdbc:mysql://localhost:3306/mbip");
 source.setUsername("root");
 source.setPassword("IP123");
 return source;
 }
 @Bean
 JdbcTemplate jdbcTemplate() {
 return new JdbcTemplate(dataSource());
 }
}
