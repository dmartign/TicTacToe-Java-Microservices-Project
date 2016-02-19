package cse551.tbd.springbootreference.service;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;

import cse551.tbd.springbootreference.Application;
import cse551.tbd.springbootreference.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class LoginRepositoryTest {

    @Autowired
    LoginRepository repository;

    @Before
    public void before() {
        this.repository.deleteAll();
    }

    @Test
    public void canSaveUser() throws Exception {
        User user = User.builder().username("username").password("password").build();

        user = this.repository.save(user);

        User actual = this.repository.findByUsername("username");
        assertThat(actual, is(user));
    }

    @Test
    public void canFindByToken() throws Exception {
        User user = User.builder().username("username").password("password").token("token").build();
        user = this.repository.save(user);

        User actual = this.repository.findByToken("token");
        assertThat(actual, is(user));
    }

    @Test
    public void canFindAllWithToken() throws Exception {
        User user = User.builder().username("username").password("password").token("token").build();
        this.repository.save(Lists.newArrayList(user, User.builder().username("other").password("password").build()));

        List<User> actual = this.repository.findByTokenIsNotNull();
        assertThat(actual, contains(user));
    }

}
