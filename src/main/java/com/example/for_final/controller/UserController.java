package com.example.for_final.controller;

import com.example.for_final.entity.User;
import com.example.for_final.repository.UserRepo;
import com.example.for_final.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/users")
@Transactional
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('COMPANY')")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/register")
    public User addUser(@RequestBody User user
    ) {
        return userService.addUser(user);
    }


}
