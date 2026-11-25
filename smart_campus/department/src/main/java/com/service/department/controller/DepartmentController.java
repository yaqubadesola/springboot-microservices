package com.service.department.controller;

import com.service.department.dto.DepartmentRequest;
import com.service.department.dto.DepartmentResponse;
import com.service.department.service.DepartmentInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/department")
public class DepartmentController {
    private final DepartmentInterface departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponse> createUser(@Valid @RequestBody DepartmentRequest department) {
        System.out.println("I got here");
        DepartmentResponse savedDept = departmentService.create(department);
        return new ResponseEntity<>(savedDept, HttpStatus.CREATED);
    }
//
//    @Operation(
//            summary = "Get a User API",
//            description = "This API get a single user"
//    )
//
//    @ApiResponse(
//            responseCode = "200",
//            description = "HTTP Status 200 Success"
//    )
//    @GetMapping("{id}")
//    public ResponseEntity<UserDto> getUsedById(@PathVariable("id") Long userId){
//        UserDto user = userService.getUserById(userId);
//        return new ResponseEntity<>(user, HttpStatus.OK);
////        if(user.isPresent()){
////            return new ResponseEntity<>(user.get(), HttpStatus.OK);
////        }
//        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        //cleaner approach
////        return userService.getUserById(userId)
////                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
////                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//
//    @Operation(
//            summary = "Get all users API",
//            description = "This API get all users"
//    )
//
//    @ApiResponse(
//            responseCode = "200",
//            description = "This API get all the users"
//    )
//
//    @GetMapping
//    public ResponseEntity<List<UserDto>> getAllUsers(){
//        List<UserDto> users = userService.getAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//
//
//    @Operation(
//            summary = "Update a User API",
//            description = "This API update already existing user with ID"
//    )
//
//    @ApiResponse(
//            responseCode = "200",
//            description = "HTTP response code - 200 updated"
//    )
//    @PutMapping("{id}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserDto userDto){
//        userDto.setId(userId);
//        UserDto updatedUser = userService.updateUser(userDto);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }
//
//    @Operation(
//            summary = "Delete a User API",
//            description = "This API delete a user"
//    )
//
//    @ApiResponse(
//            responseCode = "200",
//            description = "HTTP Response Code of 200 Success"
//    )
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
//        boolean deleted = userService.deleteUser(userId);
//
//        if (!deleted) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND)
////                    .body("User not found with ID: " + userId);
//            return new ResponseEntity<>("User not found with ID: " + userId, HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
//    }
}
