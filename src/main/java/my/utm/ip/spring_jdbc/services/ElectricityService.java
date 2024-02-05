package my.utm.ip.spring_jdbc.services;

import java.util.List;

import my.utm.ip.spring_jdbc.model.Electricity;
import my.utm.ip.spring_jdbc.model.User;

public interface ElectricityService {
    User getUserById(int id);
    List<Electricity> getElectricityByUserId(int userId);
    List<Electricity> filterElectricity(int userId, int year, String[] selectedMonths);
    Electricity getElectricityById(int billId);
    Integer getMaxElectricityId();
    void insertElectricity(Electricity electricity);
}
