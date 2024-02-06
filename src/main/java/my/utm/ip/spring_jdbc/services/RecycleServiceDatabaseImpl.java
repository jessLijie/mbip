package my.utm.ip.spring_jdbc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import my.utm.ip.spring_jdbc.model.Recycle;
import my.utm.ip.spring_jdbc.model.User;
import my.utm.ip.spring_jdbc.model.DAO.UserDAO;
import my.utm.ip.spring_jdbc.model.DAO.RecycleDAO;
import my.utm.ip.spring_jdbc.model.Repository.RecycleRepository;

@Service
public class RecycleServiceDatabaseImpl implements RecycleService {

    @Autowired
    RecycleRepository repo;

    @Override
    public List<Recycle> getRecycleByUserId(int userId) {
        List<RecycleDAO> daos = repo.getRecycleByUserId(userId);
        List<Recycle> recycleList = new ArrayList<>();
        for (RecycleDAO dao : daos) {
            recycleList.add(new Recycle(dao));
        }
        return recycleList;
    }

    @Override
    public List<Recycle> filterRecycle(int userId, int year, String[] selectedMonths) {
        List<RecycleDAO> daos = repo.filterRecycle(userId, year, selectedMonths);
        List<Recycle> recycleList = new ArrayList<>();
        for (RecycleDAO dao : daos) {
            recycleList.add(new Recycle(dao));
        }
        return recycleList;
    }

    @Override
    public Recycle getRecycleById(int billId) {
        RecycleDAO dao = repo.getRecycleById(billId);
        return new Recycle(dao);
    }

    @Override
    public Integer getMaxRecycleId() {
        return repo.getMaxRecycleId();
    }

    @Override
    public void insertRecycle(Recycle recycle) {
        repo.insertRecycle(recycle.toDAO());
    }

    @Override
    public User getUserById(int id) {
        UserDAO dao = repo.getUserById(id);
        return new User();
       
    }
}
