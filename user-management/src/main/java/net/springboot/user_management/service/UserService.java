package net.springboot.user_management.service;

import net.springboot.user_management.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long userId);
    List getAllUsers();
    User updateUser(User user);
    void deleteUser(User user);
}
