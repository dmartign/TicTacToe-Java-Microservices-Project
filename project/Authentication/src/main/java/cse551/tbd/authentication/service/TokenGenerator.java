package cse551.tbd.authentication.service;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    public String generate() {
        return UUID.randomUUID().toString();
    }

}
