package cse551.tbd.springbootreference.service;

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
            return user.getToken();
        } else {
            return null;
        }
    }

    public void logout(String token) {
        // TODO Auto-generated method stub

    }

}
