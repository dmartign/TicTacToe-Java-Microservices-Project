package cse551.tbd.springbootreference.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cse551.tbd.springbootreference.controller.dto.LoginRequest;
import cse551.tbd.springbootreference.controller.dto.LogoutResponse;
import cse551.tbd.springbootreference.service.LoginService;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = POST)
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = this.loginService.login(request.getUsername(), request.getPassword());
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    @RequestMapping(value = "/{token}", method = DELETE)
    public ResponseEntity<LogoutResponse> logout(@PathVariable("token") String token) {
        this.loginService.logout(token);
        return new ResponseEntity<LogoutResponse>(HttpStatus.OK);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<String>> getLoggedInUsers() {
        return new ResponseEntity<List<String>>(this.loginService.getLoggedInUsers(), HttpStatus.OK);
    }

    // should this track token to IP?
    // Add interceptor to make sure user is logged in with valid token before
    // processing below
    // Add method to GET list of logged in users
    // Query if a token is valid

}
