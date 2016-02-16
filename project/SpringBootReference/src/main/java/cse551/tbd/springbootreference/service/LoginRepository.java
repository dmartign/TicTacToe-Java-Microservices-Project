package cse551.tbd.springbootreference.service;

import org.springframework.stereotype.Component;

import cse551.tbd.springbootreference.domain.User;

@Component
public interface LoginRepository extends org.springframework.data.repository.CrudRepository<User, String> {

    public User findByUsername(String username);

    public User findByToken(String token);

}
