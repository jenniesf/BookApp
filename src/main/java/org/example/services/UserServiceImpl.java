package org.example.services;

import org.example.dtos.UserDto;
import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// business logic - add new user and check cred/auth during login

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // register a user
    @Override
    @Transactional
    public List<String> addUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        User user = new User(userDto);          //turn dto into User
        userRepository.saveAndFlush(user);      //user is persisted
        response.add("http://localhost:8080/login.html");
        return response;                        //return List<String>
    }
    // logging into user account
    @Override
    public List<String> userLogin(UserDto userDto){
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());   //find in DB
        if(userOptional.isPresent()){
            if(passwordEncoder.matches(userDto.getPassword() , userOptional.get().getPassword()) ) {
                response.add("http://localhost:8080/home.html");
                response.add(String.valueOf(userOptional.get().getUser_id()));
            } else {
                response.add("Username or password incorrect");
            }
        } else {
            response.add("Username or password incorrect");
        }
        return response;
    }
}
