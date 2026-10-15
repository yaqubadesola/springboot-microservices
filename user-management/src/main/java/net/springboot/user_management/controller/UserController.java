package net.springboot.user_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.springboot.user_management.dto.UserDto;
import net.springboot.user_management.entity.User;
import net.springboot.user_management.exception.ErrorDetails;
import net.springboot.user_management.exception.ResourceNotFoundException;
import net.springboot.user_management.mapper.UserMapper;
import net.springboot.user_management.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Tag(
        name = "CRUD REST API for User Resource",
        description = "CRUD REST API - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Operation(
        summary = "Create a User API",
        description = "This API create a new user"
    )

    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status Created - 201"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get a User API",
            description = "This API get a single user"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 Success"
    )
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


    @Operation(
            summary = "Get all users API",
            description = "This API get all users"
    )

    @ApiResponse(
            responseCode = "200",
            description = "This API get all the users"
    )

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @Operation(
            summary = "Update a User API",
            description = "This API update already existing user with ID"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP response code - 200 updated"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserDto userDto){
        userDto.setId(userId);
        UserDto updatedUser = userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a User API",
            description = "This API delete a user"
    )

    @ApiResponse(
            responseCode = "200",
            description = "HTTP Response Code of 200 Success"
    )
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
