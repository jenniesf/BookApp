package org.example.controllers;

import org.example.dtos.BookDto;
import org.example.dtos.CurrentDto;
import org.example.services.BookService;
import org.example.services.CurrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/current")
public class CurrentController {

    @Autowired
    private CurrentService currentService;

    // POST a current book by user id
    @PostMapping("/user/{userId}/{bookId}")
    public void addBook(@RequestBody CurrentDto currentDto, @PathVariable Long userId , @PathVariable Long bookId){
        currentService.addCurrent(currentDto, userId, bookId);
    }

    // DELETE a current by current id
    @DeleteMapping("/{currentId}")
    public void deleteCurrentById(@PathVariable Long currentId){
        currentService.deleteCurrentById(currentId);
    }

    // UPDATE/PUT a current by current id
    @PutMapping
    public void updateCurrent(@RequestBody CurrentDto currentDto){
        currentService.updateCurrentById(currentDto);
    }

    // GET all current by user id
    @GetMapping("/user/{userId}")
    public List<CurrentDto> getCurrentByUser(@PathVariable Long userId){
        return currentService.getAllCurrentByUserId(userId);
    }

    // GET current by current id
    @GetMapping("/{currentId}")
    public Optional<CurrentDto> getCurrentById(@PathVariable Long currentId){
        return currentService.getCurrentById(currentId);
    }
}
