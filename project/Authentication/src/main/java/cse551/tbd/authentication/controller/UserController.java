package cse551.tbd.authentication.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cse551.tbd.authentication.domain.User;
import cse551.tbd.authentication.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = POST)
    public ResponseEntity<Boolean> create(@RequestBody User user) {
        boolean userCreated = this.userService.create(user);
        return new ResponseEntity<Boolean>(userCreated, HttpStatus.OK);
    }

}
