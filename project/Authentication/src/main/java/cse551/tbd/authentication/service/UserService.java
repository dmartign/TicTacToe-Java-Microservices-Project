package cse551.tbd.authentication.service;

import static cse551.tbd.authentication.Utils.nullOrBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cse551.tbd.authentication.domain.User;

@Component
public class UserService {

    @Autowired
    LoginRepository repository;

    public boolean create(User user) {
        if (nullOrBlank(user.getPassword()) || nullOrBlank(user.getUsername()) || nullOrBlank(user.getEmail())) {
            throw new IllegalArgumentException("Not all fields filled");
        }
        if (this.repository.findByUsername(user.getUsername()) == null) {
            this.repository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
