package my.utm.ip.spring_jdbc.model.Repository;

import java.util.List;
import my.utm.ip.spring_jdbc.model.DAO.ElectricityDAO;
import my.utm.ip.spring_jdbc.model.DAO.UserDAO;


public interface ElectricityRepository {
    UserDAO getUserById(int id);
    List<ElectricityDAO> getElectricityByUserId(int userId);
    List<ElectricityDAO> filterElectricity(int userId, int year, String[] selectedMonths);
    ElectricityDAO getElectricityById(int billId);
    Integer getMaxElectricityId();
    void insertElectricity(ElectricityDAO electricity);
}
    
