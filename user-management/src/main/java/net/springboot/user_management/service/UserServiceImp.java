package net.springboot.user_management.service;

import lombok.AllArgsConstructor;
import net.springboot.user_management.dto.UserDto;
import net.springboot.user_management.entity.User;
import net.springboot.user_management.exception.EmailAlreadyExistsException;
import net.springboot.user_management.exception.ResourceNotFoundException;
import net.springboot.user_management.mapper.AutoUserMapper;
import net.springboot.user_management.mapper.UserMapper;
import net.springboot.user_management.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{

    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        //before saving map userDto to user entity
        //User newUser = UserMapper.mapToUser(userDto);
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException(userDto.getEmail());
        }
        User newUser = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(newUser);

        //Map user to userDto before returning response
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user =  userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("user", "userId", userId )
        );
        //UserDto retrievedUserDto =  UserMapper.mapToUserDto(user.get());
        UserDto retrievedUserDto =  AutoUserMapper.MAPPER.mapToUserDto(user);
        return retrievedUserDto;

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
//        return users.stream().map(user -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
        return users.stream().map(user -> AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
//        User userToUpdate = UserMapper.mapToUser(user);
        User existedUser =  userRepository.findById(user.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("user", "userId", user.getId() )
        );
        existedUser.setFirstName(user.getFirstName());
        existedUser.setLastName(user.getLastName());
        existedUser.setEmail(user.getEmail());
        //User userToUpdate = AutoUserMapper.MAPPER.mapToUser(existedUser);
        User updatedUser = userRepository.save(existedUser);
//        return UserMapper.mapToUserDto((updatedUser));
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public Boolean deleteUser(Long userId) {
//        Optional<User> user = userRepository.findById(userId);
//
//        if (user.isEmpty()) {
//            return false;
//        }
        User existedUser =  userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("user", "userId", userId )
        );
        userRepository.delete(existedUser);
        return true;
    }
}
