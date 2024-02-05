package my.utm.ip.spring_jdbc.services;

import java.util.List;

import my.utm.ip.spring_jdbc.model.Recycle;
import my.utm.ip.spring_jdbc.model.User;

public interface RecycleService {
    User getUserById(int id);
    List<Recycle> getRecycleByUserId(int userId);
    List<Recycle> filterRecycle(int userId, int year, String[] selectedMonths);
    Recycle getRecycleById(int billId);
    Integer getMaxRecycleId();
    void insertRecycle(Recycle electricity);
}
