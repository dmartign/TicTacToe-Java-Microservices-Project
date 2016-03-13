package hw3q1.model.domain.dao;

import hw3q1.model.domain.User;

public interface UserDAO {

    public User findUserByEmail(String email);

    public void register(User user);

}
