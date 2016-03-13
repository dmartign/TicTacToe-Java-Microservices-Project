package hw3q1.model.domain.dao;

import hw3q1.model.domain.User;

import java.util.HashMap;
import java.util.Map;

public class CrappyUserDAO implements UserDAO {

    Map<String, User> users = new HashMap<String, User>();

    @Override
    public User findUserByEmail(String email) {
        return this.users.get(email);
    }

    @Override
    public void register(User user) {
        this.users.put(user.getEmail(), user);
    }
}
