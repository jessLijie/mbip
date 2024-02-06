package my.utm.ip.spring_jdbc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import my.utm.ip.spring_jdbc.model.Event;
import my.utm.ip.spring_jdbc.model.User;
import my.utm.ip.spring_jdbc.model.DAO.UserDAO;
import my.utm.ip.spring_jdbc.model.Repository.ElectricityRepository;
import my.utm.ip.spring_jdbc.model.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
     @Autowired
    UserRepository repo;

    @Override
    public User getUserById(int id) {
        UserDAO dao = repo.getUserById(id);
        return new User();
       
    }
}