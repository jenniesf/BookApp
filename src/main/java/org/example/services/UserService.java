package org.example.services;

import org.example.dtos.UserDto;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    // register a user
    @Transactional
    List<String> addUser(UserDto userDto);

    // logging into user account
    List<String> userLogin(UserDto userDto);
}
