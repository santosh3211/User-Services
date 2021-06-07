package com.epam.user.service.impl;

import com.epam.user.exceptions.EmptyInputException;
import com.epam.user.model.User;
import com.epam.user.repos.UserRepo;
import com.epam.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public List<User> createUsers(List<User> users) {
        return userRepo.saveAll(users);
    }

    @Override
    public User createUser(User user) {
        if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getFirstName())) {
            throw new EmptyInputException("400", "Input Fields are empty");
        }
        return userRepo.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getFirstName())) {
            throw new EmptyInputException("400", "Input Fields are empty");
        }
        User existingUser = userRepo.findById(userId).orElse(null);
        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return userRepo.save(existingUser);
    }

    @Override
    public User findByUserId(Long userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }
}
