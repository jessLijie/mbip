package my.utm.ip.spring_jdbc.services;

import org.springframework.stereotype.Service;

import my.utm.ip.spring_jdbc.model.User;

@Service
public interface UserService {

    public User getUserById(int userid);

}
