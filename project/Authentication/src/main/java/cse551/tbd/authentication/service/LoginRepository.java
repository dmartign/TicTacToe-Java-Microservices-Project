package cse551.tbd.authentication.service;

import java.util.List;

import org.springframework.stereotype.Component;

import cse551.tbd.authentication.domain.User;

@Component
public interface LoginRepository extends org.springframework.data.repository.CrudRepository<User, String> {

    public User findByUsername(String username);

    public User findByToken(String token);

    public List<User> findByTokenIsNotNull();

}
