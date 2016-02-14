package cse551.tbd.springbootreference.service;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    public String generate() {
        return UUID.randomUUID().toString();
    }

}
