package net.springboot.user_management.service;

import lombok.AllArgsConstructor;
import net.springboot.user_management.entity.User;
import net.springboot.user_management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{

    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return  userRepository.findById(userId);

    }

    @Override
    public List getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
