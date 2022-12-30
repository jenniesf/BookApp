package org.example.controllers;

import org.example.dtos.UserDto;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // POST request to register a new user
    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto){
        String passHash = passwordEncoder.encode(userDto.getPassword());    //encode pw coming in
        userDto.setPassword(passHash);                                      //set encoded pw
        return userService.addUser(userDto);
    }

    // POST request to log in a user
    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto){
        return userService.userLogin(userDto);
    }
}
