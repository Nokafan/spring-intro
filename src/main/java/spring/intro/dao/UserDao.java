package spring.intro.dao;

import java.util.List;
import spring.intro.model.User;

public interface UserDao {
    User add(User user);

    User getById(Long id);

    List<User> listUsers();
}
