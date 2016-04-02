package tbd.gateway.model.domain.dao;

import tbd.gateway.model.domain.User;

public interface UserDAO {

    public User findUserByEmail(String email);

    public void register(User user);

}
