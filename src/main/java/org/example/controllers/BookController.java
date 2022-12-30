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

    // GET books on bookshelf if true by user id
    @GetMapping("/bookshelf/user/{userId}")
    public List<BookDto> getBookshelfByUser(@PathVariable Long userId){
        return bookService.getBooksByUserAndBookshelf(userId, true);
    }

    // GET review if bookshelf is false by user id
    @GetMapping("/review/user/{userId}")
    public List<BookDto> getReviewByUser(@PathVariable Long userId){
        return bookService.getBooksByUserAndBookshelf(userId, false);
    }

    // GET reviews for Feed if bookshelf is false and reviews does not belong to requesting user
    @GetMapping("/feed/user/{userId}")
    public List<BookDto> getReviewNotByUser(@PathVariable Long userId){
        // get List of Feed of books
        var listOfBooks = bookService.getBooksByNonUserAndBookshelf(userId, false);
        // System.out.println(listOfBooks);
        // create a hashset that has the book review + the user's first name
        // hashset key will be user id
        return listOfBooks;
    }


    // GET book by book id
    @GetMapping("/{bookId}")
    public Optional<BookDto> getBookById(@PathVariable Long bookId){
        return bookService.getBookById(bookId);
    }
}
