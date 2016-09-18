package cse551.tbd.authentication.service;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import cse551.tbd.authentication.domain.User;
import cse551.tbd.authentication.service.UserNameExtractor;

public class UserNameExtractorTest {

    UserNameExtractor extractor = new UserNameExtractor();

    @Test
    public void canExtractUserNames() throws Exception {

        List<User> users = Lists.<User> newArrayList(User.builder().username("A").build(), User.builder().username("B").build());

        List<String> actual = this.extractor.extract(users);

        assertThat(actual, contains("A", "B"));
    }

}
