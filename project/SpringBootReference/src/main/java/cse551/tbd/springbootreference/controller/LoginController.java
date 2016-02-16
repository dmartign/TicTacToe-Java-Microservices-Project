package cse551.tbd.springbootreference.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cse551.tbd.springbootreference.controller.dto.LoginRequest;
import cse551.tbd.springbootreference.controller.dto.LoginResponse;
import cse551.tbd.springbootreference.controller.dto.LogoutResponse;
import cse551.tbd.springbootreference.service.LoginService;

@RestController(value = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = POST)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = this.loginService.login(request.getUsername(), request.getPassword());
        return new ResponseEntity<LoginResponse>(new LoginResponse(token), HttpStatus.OK);
    }

    @RequestMapping(value = "/{token}", method = DELETE)
    public ResponseEntity<LogoutResponse> logout(@RequestParam("token") String token) {
        this.loginService.logout(token);
        return new ResponseEntity<LogoutResponse>(HttpStatus.OK);
    }

}
