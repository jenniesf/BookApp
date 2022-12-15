package org.example.controllers;

import org.example.GoogleBook;
import org.example.services.GoogleBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// CONTROLLERS - define REST API endpoints and creating
// the paths that can deliver up the information to the client
@RestController
@RequestMapping("/api/v1/bookapi")
public class GoogleBookController {

    @Autowired
    private GoogleBookService googleBookService;

    @PostMapping("/")
    public ArrayList<GoogleBook> searchGoogleBook(@RequestBody String userRequest) throws Exception {
        return googleBookService.searchBook(userRequest);
    }

}
