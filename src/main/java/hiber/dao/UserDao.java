package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   void remove (Long id);
   User getUserWithModelAndSeries(String car_model, Integer car_series);

}
