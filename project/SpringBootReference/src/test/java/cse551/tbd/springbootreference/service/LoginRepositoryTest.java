package cse551.tbd.springbootreference.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cse551.tbd.springbootreference.Application;
import cse551.tbd.springbootreference.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class LoginRepositoryTest {

    @Autowired
    LoginRepository repository;

    @Test
    public void canSaveUser() throws Exception {
        User user = User.builder().username("username").password("password").build();

        user = this.repository.save(user);

        User actual = this.repository.findByUsername("username");
        assertThat(actual, is(user));
    }

}
