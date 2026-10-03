package net.springboot.user_management.controller;

import lombok.AllArgsConstructor;
import net.springboot.user_management.entity.User;
import net.springboot.user_management.service.UserService;
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
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUsedById(@PathVariable("id") Long userId){
        Optional<User> user = userService.getUserById(userId);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //cleaner approach
//        return userService.getUserById(userId)
//                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody User user){
        user.setId(userId);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        Optional<User> user = userService.getUserById(userId);
        if(user.isPresent()) {
            userService.deleteUser(user.get());
            return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }


    }
}
