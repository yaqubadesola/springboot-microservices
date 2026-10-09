package net.springboot.user_management.mapper;

import net.springboot.user_management.dto.UserDto;
import net.springboot.user_management.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);//It provides implementation
    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);
}
