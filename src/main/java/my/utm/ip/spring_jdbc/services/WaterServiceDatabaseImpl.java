package my.utm.ip.spring_jdbc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import my.utm.ip.spring_jdbc.model.Water;
import my.utm.ip.spring_jdbc.model.User;
import my.utm.ip.spring_jdbc.model.DAO.UserDAO;
import my.utm.ip.spring_jdbc.model.DAO.WaterDAO;
import my.utm.ip.spring_jdbc.model.Repository.WaterRepository;

@Service
public class WaterServiceDatabaseImpl implements WaterService {

    @Autowired
    WaterRepository repo;

    @Override
    public List<Water> getWaterByUserId(int userId) {
        List<WaterDAO> daos = repo.getWaterByUserId(userId);
        List<Water> waterList = new ArrayList<>();
        for (WaterDAO dao : daos) {
            waterList.add(new Water(dao));
        }
        return waterList;
    }

    @Override
    public List<Water> filterWater(int userId, int year, String[] selectedMonths) {
        List<WaterDAO> daos = repo.filterWater(userId, year, selectedMonths);
        List<Water> waterList = new ArrayList<>();
        for (WaterDAO dao : daos) {
            waterList.add(new Water(dao));
        }
        return waterList;
    }

    @Override
    public Water getWaterById(int billId) {
        WaterDAO dao = repo.getWaterById(billId);
        return new Water(dao);
    }

    @Override
    public Integer getMaxWaterId() {
        return repo.getMaxWaterId();
    }

    @Override
    public void insertWater(Water water) {
        repo.insertWater(water.toDAO());
    }

    @Override
    public User getUserById(int id) {
        UserDAO dao = repo.getUserById(id);
        return new User();
       
    }
}
