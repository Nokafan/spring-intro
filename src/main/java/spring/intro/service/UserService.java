package spring.intro.service;

import java.util.List;
import spring.intro.model.User;

public interface UserService {
    User add(User user);

    User getById(Long id);

    List<User> listUsers();
}
