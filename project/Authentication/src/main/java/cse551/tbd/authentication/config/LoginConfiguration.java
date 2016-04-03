package cse551.tbd.authentication.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import cse551.tbd.authentication.domain.User;
import cse551.tbd.authentication.service.LoginRepository;

@Configuration
public class LoginConfiguration implements InitializingBean {

    @Autowired
    LoginRepository loginRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.loginRepository.save(User.builder().username("Player1").password("password").token("1").build());
        this.loginRepository.save(User.builder().username("Player2").password("password").token("2").build());
    }

}
