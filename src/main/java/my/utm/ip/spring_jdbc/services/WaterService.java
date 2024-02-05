package my.utm.ip.spring_jdbc.services;

import java.util.List;

import my.utm.ip.spring_jdbc.model.Water;
import my.utm.ip.spring_jdbc.model.User;

public interface WaterService {
    User getUserById(int id);
    List<Water> getWaterByUserId(int userId);
    List<Water> filterWater(int userId, int year, String[] selectedMonths);
    Water getWaterById(int billId);
    Integer getMaxWaterId();
    void insertWater(Water water);
}
