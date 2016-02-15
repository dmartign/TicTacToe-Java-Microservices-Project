package cse551.tbd.springbootreference.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cse551.tbd.springbootreference.controller.dto.LoginRequest;
import cse551.tbd.springbootreference.controller.dto.LoginResponse;
import cse551.tbd.springbootreference.controller.dto.LogoutRequest;
import cse551.tbd.springbootreference.controller.dto.LogoutResponse;
import cse551.tbd.springbootreference.service.LoginService;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = this.loginService.login(request.getUsername(), request.getPassword());
        return new ResponseEntity<LoginResponse>(new LoginResponse(token), HttpStatus.OK);
    }

    @RequestMapping(value = "/logout")
    public ResponseEntity<LogoutResponse> logout(@RequestBody LogoutRequest request) {
        this.loginService.logout(request.getToken());
        return new ResponseEntity<LogoutResponse>(HttpStatus.OK);
    }

    // should this track token to IP?
    // Add interceptor to make sure user is logged in with valid token before
    // processing below
    // Add method to GET list of logged in users
    // Query if a token is valid

}
