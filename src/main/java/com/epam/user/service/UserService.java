package com.epam.user.service;

import com.epam.user.model.User;

import java.util.List;


public interface UserService {
    List<User> getAllUser();

    List<User> createUsers(List<User> users);

    User createUser(User user);
    User updateUser(Long  userId, User user);

    User findByUserId(Long userId);

    void deleteUser(Long userId);
}
