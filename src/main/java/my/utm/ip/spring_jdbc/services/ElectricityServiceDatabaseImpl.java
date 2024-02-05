package my.utm.ip.spring_jdbc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import my.utm.ip.spring_jdbc.model.Electricity;
import my.utm.ip.spring_jdbc.model.User;
import my.utm.ip.spring_jdbc.model.DAO.UserDAO;
import my.utm.ip.spring_jdbc.model.DAO.ElectricityDAO;
import my.utm.ip.spring_jdbc.model.Repository.ElectricityRepository;

@Service
public class ElectricityServiceDatabaseImpl implements ElectricityService {

    @Autowired
    ElectricityRepository repo;

    @Override
    public List<Electricity> getElectricityByUserId(int userId) {
        List<ElectricityDAO> daos = repo.getElectricityByUserId(userId);
        List<Electricity> electricityList = new ArrayList<>();
        for (ElectricityDAO dao : daos) {
            electricityList.add(new Electricity(dao));
        }
        return electricityList;
    }

    @Override
    public List<Electricity> filterElectricity(int userId, int year, String[] selectedMonths) {
        List<ElectricityDAO> daos = repo.filterElectricity(userId, year, selectedMonths);
        List<Electricity> electricityList = new ArrayList<>();
        for (ElectricityDAO dao : daos) {
            electricityList.add(new Electricity(dao));
        }
        return electricityList;
    }

    @Override
    public Electricity getElectricityById(int billId) {
        ElectricityDAO dao = repo.getElectricityById(billId);
        return new Electricity(dao);
    }

    @Override
    public Integer getMaxElectricityId() {
        return repo.getMaxElectricityId();
    }

    @Override
    public void insertElectricity(Electricity electricity) {
        repo.insertElectricity(electricity.toDAO());
    }

    @Override
    public User getUserById(int id) {
        UserDAO dao = repo.getUserById(id);
        return new User();
       
    }
}
