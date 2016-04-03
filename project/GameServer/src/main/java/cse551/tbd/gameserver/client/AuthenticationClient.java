package cse551.tbd.gameserver.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

import cse551.tbd.gameserver.domain.User;

@Component
public class AuthenticationClient {

    @Autowired
    private RestTemplate restTemplate;

    public User retrieveUser(String userToken) {
        Map<String, String> urlVariables = Maps.newHashMap();
        urlVariables.put("userToken", userToken);
        ResponseEntity<User> response = this.restTemplate.getForEntity("http://localhost:9001/authentication/login/{userToken}", User.class, urlVariables);

        return response.getBody();
    }
}
