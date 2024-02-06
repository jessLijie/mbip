package my.utm.ip.spring_jdbc.model.Repository;

import my.utm.ip.spring_jdbc.model.DAO.UserDAO;

public interface UserRepository {
        public UserDAO getUserById(int id);
}
