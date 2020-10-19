package spring.intro.dao.impl;

import java.util.List;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.intro.dao.UserDao;
import spring.intro.model.User;

@Log4j
@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        log.info("Calling method add for " + user);
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            log.info("Insert successful for " + user);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                }
            throw new RuntimeException("Transaction rollbacked for user " + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
            return user;
    }

    @Override
    public List<User> listUsers() {
        log.info("Calling method listUsers()");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User ", User.class).getResultList();
        }
    }
}
