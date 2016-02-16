package cse551.tbd.springbootreference.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cse551.tbd.springbootreference.domain.User;

@Component
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    TokenGenerator tokenGenerator;

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

}
