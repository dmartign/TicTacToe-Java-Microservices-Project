package cse551.tbd.authentication.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cse551.tbd.authentication.domain.User;

@Component
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    TokenGenerator tokenGenerator;

    @Autowired
    UserNameExtractor userNameExtractor;

    public String login(String username, String password) {
        User user = this.loginRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            user.setToken(this.tokenGenerator.generate());
            user = this.loginRepository.save(user);
            Logger.getLogger(LoginService.class).info("Logging in " + user.getUsername());
            return user.getToken();
        } else {
            return null;
        }
    }

    public void logout(String token) {
        User user = this.loginRepository.findByToken(token);
        if (user != null) {
            Logger.getLogger(LoginService.class).info("Logging out " + user.getUsername());
            user.setToken(null);
            this.loginRepository.save(user);
        }
    }

    public List<String> getLoggedInUsers() {
        List<User> users = this.loginRepository.findByTokenIsNotNull();
        return this.userNameExtractor.extract(users);
    }

    public User getUser(String token) {
        User user = this.loginRepository.findByToken(token);
        return user;
    }

}
