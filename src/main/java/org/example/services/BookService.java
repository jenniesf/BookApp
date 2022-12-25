package org.example.services;

import org.example.dtos.BookDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BookService {
    // add a book by user id
    @Transactional
    void addBook(BookDto bookDto, Long userId);

    // delete a book by book id
    @Transactional
    void deleteBookById(Long bookId);

    // update a book (review) by book id
    @Transactional
    void updateBookById(BookDto bookDto);

    // find all books by user id
    List<BookDto> getAllBooksByUserId(Long userId);

    // find all books by user id AND bookshelf is true OR reviews if bookshelf is false
    List<BookDto> getBooksByUserAndBookshelf(Long userId, boolean booleanPassed);

    // find book by book id
    Optional<BookDto> getBookById(Long bookId);
}
