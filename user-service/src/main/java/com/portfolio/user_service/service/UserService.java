package com.portfolio.user_service.service;

import com.portfolio.user_service.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    User updateUser(Long id,User user);

    void deleteUser(Long id);
}
