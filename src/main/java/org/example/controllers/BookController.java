package org.example.controllers;

import org.example.dtos.BookDto;
import org.example.services.BookService;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // POST a book by user id
    @PostMapping("/user/{userId}")
    public void addBook(@RequestBody BookDto bookDto, @PathVariable Long userId){
        bookService.addBook(bookDto, userId);
    }

    // DELETE a book by book id
    @DeleteMapping("/{bookId}")
    public void deleteBookById(@PathVariable Long bookId){
        bookService.deleteBookById(bookId);
    }

    // UPDATE (PUT) a book review by book id
    @PutMapping
    public void updateBook(@RequestBody BookDto bookDto){
        bookService.updateBookById(bookDto);
    }

    // GET all books by user id
    @GetMapping("/user/{userId}")
    public List<BookDto> getBooksByUser(@PathVariable Long userId){
        return bookService.getAllBooksByUserId(userId);
    }

    // GET book by book id
    @GetMapping("/{bookId}")
    public Optional<BookDto> getBookById(@PathVariable Long bookId){
        return bookService.getBookById(bookId);
    }
}
