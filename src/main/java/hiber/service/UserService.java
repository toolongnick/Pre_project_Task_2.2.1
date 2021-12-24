package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    void remove (Long id);
    User getUser(String car_model, Integer car_series);

}
