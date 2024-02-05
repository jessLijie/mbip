package my.utm.ip.spring_jdbc.model.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import my.utm.ip.spring_jdbc.model.DAO.RecycleDAO;
import my.utm.ip.spring_jdbc.model.DAO.UserDAO;


public interface RecycleRepository {
    UserDAO getUserById(int id);
    List<RecycleDAO> getRecycleByUserId(int userId);
    List<RecycleDAO> filterRecycle(int userId, int year, String[] selectedMonths);
    RecycleDAO getRecycleById(int billId);
    Integer getMaxRecycleId();
    void insertRecycle(RecycleDAO electricity);
}
    
