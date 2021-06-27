package com.epam.user.controllers;


import com.epam.user.model.User;
import com.epam.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userapi")
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Fetching users from user database");
        List<User> users = userService.getAllUser();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{user_id}")
    public ResponseEntity<?> getUserById(@PathVariable("user_id") Long userId) {
        logger.info("Fetching user by Id from user database");
        Optional<User> userRetrived = Optional.ofNullable(userService.findByUserId(userId));
        return new ResponseEntity<Optional<User>>(userRetrived, HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<List<User>>createUsers(@RequestBody List<User> users) {
        logger.info("Bulk user creation started..");
        List<User> userList = userService.createUsers(users);
        return new ResponseEntity<List<User>>(userList, HttpStatus.CREATED);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        logger.info("User creation started..");
        User usercreated = userService.createUser(user);
        return new ResponseEntity<User>(usercreated, HttpStatus.CREATED);
    }

    @PutMapping(value = "/users/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable("user_id") Long userId, @RequestBody User user) {
        logger.info("Updating user by id : " + userId);
        User userSaved = userService.updateUser(userId, user);
        return new ResponseEntity<User>(userSaved, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/{user_id}")
    public ResponseEntity<Void>  deleteUser(@PathVariable("user_id") Long userId) {
        logger.info("Remove user by id : "+userId);
        userService.deleteUser(userId);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }


}
