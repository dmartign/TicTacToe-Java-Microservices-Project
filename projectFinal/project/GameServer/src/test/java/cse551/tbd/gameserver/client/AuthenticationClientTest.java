package cse551.tbd.gameserver.client;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

import cse551.tbd.gameserver.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationClientTest {

    @InjectMocks
    AuthenticationClient authClient;

    @Mock
    RestTemplate restTemplate;

    @Test
    public void shouldRetrieveUser() throws Exception {
        String userToken = "token";

        User expected = new User("User");

        Map<String, String> urlVariables = Maps.newHashMap();
        urlVariables.put("userToken", userToken);
        ResponseEntity<User> response = new ResponseEntity<User>(expected, HttpStatus.OK);

        when(this.restTemplate.getForEntity("http://localhost:9001/authentication/login/{userToken}", User.class, urlVariables)).thenReturn(response);

        User actual = this.authClient.retrieveUser(userToken);

        assertThat(actual, is(expected));
    }
}
