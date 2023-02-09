package com.example.for_final.service.user;

import com.example.for_final.entity.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getAllUsers();
    User addUser(User user);
}
