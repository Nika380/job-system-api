package com.example.for_final.service.user;

import com.example.for_final.RoleAndTypes.AccountType;
import com.example.for_final.entity.Permission;
import com.example.for_final.entity.User;
import com.example.for_final.repository.UserRepo;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User addUser(User user) {
        var permission = new Permission();
        permission.setPermissionName(user.getAccountType().toString());
        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(permission);
        user.setPermissions(permissionList);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
}
