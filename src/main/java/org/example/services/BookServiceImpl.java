package org.example.services;

import org.example.dtos.BookDto;
import org.example.entities.Book;
import org.example.entities.User;
import org.example.repositories.BookRepository;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    // ADD (POST) a book by user id
    @Override
    @Transactional
    public void addBook(BookDto bookDto, Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        // System.out.println("Printing user optional: " + userOptional);
        Book book = new Book(bookDto);
        userOptional.ifPresent(book::setUser);
        bookRepository.saveAndFlush(book);
    }
    // DELETE a book by book id
    @Override
    @Transactional
    public void deleteBookById(Long bookId){
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        bookOptional.ifPresent(book -> bookRepository.delete(book));
    }
    // UPDATE (PUT) a book (review) by book id
    @Override
    @Transactional
    public void updateBookById(BookDto bookDto){
        Optional<Book> bookOptional = bookRepository.findById(bookDto.getBook_id());
        bookOptional.ifPresent( book -> {
            book.setReview(bookDto.getReview());
            bookRepository.saveAndFlush(book);
        });
    }
    // GET all books by user id
    @Override
    public List<BookDto> getAllBooksByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            List<Book> bookList = bookRepository.findAllByUserEquals(userOptional.get());
            return bookList.stream().map(book -> new BookDto(book)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    // GET book by book id
    @Override
    public Optional<BookDto> getBookById(Long bookId){
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isPresent()){
            return Optional.of(new BookDto(bookOptional.get()));
        }
        return Optional.empty();
    }
}
