package my.utm.ip.spring_jdbc.model.Repository;

import java.util.List;
import my.utm.ip.spring_jdbc.model.DAO.WaterDAO;
import my.utm.ip.spring_jdbc.model.DAO.UserDAO;


public interface WaterRepository {
    UserDAO getUserById(int id);
    List<WaterDAO> getWaterByUserId(int userId);
    List<WaterDAO> filterWater(int userId, int year, String[] selectedMonths);
    WaterDAO getWaterById(int billId);
    Integer getMaxWaterId();
    void insertWater(WaterDAO electricity);
}
    
