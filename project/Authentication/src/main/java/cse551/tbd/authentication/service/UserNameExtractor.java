package cse551.tbd.authentication.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import cse551.tbd.authentication.domain.User;

@Component
public class UserNameExtractor {

    public List<String> extract(List<User> users) {
        List<String> userNames = Lists.newArrayList();
        for (User user : users) {
            userNames.add(user.getUsername());
        }
        return userNames;
    }

}
