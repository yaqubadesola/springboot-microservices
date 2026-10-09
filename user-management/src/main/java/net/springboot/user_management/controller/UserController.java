package net.springboot.user_management.controller;

import lombok.AllArgsConstructor;
import net.springboot.user_management.dto.UserDto;
import net.springboot.user_management.entity.User;
import net.springboot.user_management.mapper.UserMapper;
import net.springboot.user_management.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUsedById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
//        if(user.isPresent()){
//            return new ResponseEntity<>(user.get(), HttpStatus.OK);
//        }
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //cleaner approach
//        return userService.getUserById(userId)
//                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto userDto){
        userDto.setId(userId);
        UserDto updatedUser = userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        boolean deleted = userService.deleteUser(userId);

        if (!deleted) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("User not found with ID: " + userId);
            return new ResponseEntity<>("User not found with ID: " + userId, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
}
