package net.springboot.user_management.mapper;

import net.springboot.user_management.dto.UserDto;
import net.springboot.user_management.entity.User;

public class UserMapper {
    //Converting User to UserDTO for response
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }


    //Converting UserDTO to user entity for request
    public static User mapToUser(UserDto userDto){
        User user = new User(
                    userDto.getId(),
                    userDto.getFirstName(),
                    userDto.getLastName(),
                    userDto.getEmail()
                );
        return user;
    }
}
