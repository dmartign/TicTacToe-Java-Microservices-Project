package cse551.tbd.springbootreference.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import cse551.tbd.springbootreference.domain.User;
import cse551.tbd.springbootreference.service.LoginRepository;

@Configuration
public class LoginConfiguration implements InitializingBean {

    @Autowired
    LoginRepository loginRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.loginRepository.save(User.builder().username("peter").password("password").build());
    }

}
