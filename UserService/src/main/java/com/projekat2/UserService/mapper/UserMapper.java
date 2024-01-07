package com.projekat2.UserService.mapper;

import com.projekat2.UserService.domain.User;
import com.projekat2.UserService.dto.UserCreateDto;
import com.projekat2.UserService.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto userToUserDto(User user){
        return new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(), user.getUsername());
    }


    public User userCreateDtoToUser(UserCreateDto userCreateDto) {
       User user = new User();
       user.setEmail(userCreateDto.getEmail());
       user.setLastName(userCreateDto.getLastName());
       user.setFirstName(userCreateDto.getFirstName());
       user.setUsername(userCreateDto.getUsername());
       user.setPassword(userCreateDto.getPassword());
       return user;
    }
}
