package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class UserDaoImp implements UserDao {

    static Logger LOGGER;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void remove(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        session.delete(user);
    }

    public User getUserWithModelAndSeries(String car_model, Integer car_series) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User u where u.car.model =:model and u.car.series =:series", User.class);
        query.setParameter("model", car_model);
        query.setParameter("series", car_series);
        if (query.getResultList().size() > 1) {
            LOGGER.log(Level.INFO, "There are more then one user...getting first one out of the list");
        }
        return (User) query.getResultList().get(0);
    }

    @Override
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
    }

}
