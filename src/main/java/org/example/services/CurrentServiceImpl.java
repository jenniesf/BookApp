package org.example.services;

import org.example.dtos.BookDto;
import org.example.dtos.CurrentDto;
import org.example.entities.Book;
import org.example.entities.Current;
import org.example.entities.User;
import org.example.repositories.BookRepository;
import org.example.repositories.CurrentRepository;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrentServiceImpl implements CurrentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CurrentRepository currentRepository;

    // ADD/POST a current 'book' by user id and book id
    @Override
    @Transactional
    public void addCurrent(CurrentDto currentDto, Long userId, Long bookId){
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        // System.out.println("Printing user optional: " + userOptional);
        Current current = new Current(currentDto);
        userOptional.ifPresent(current::setUser);
        bookOptional.ifPresent(current::setBook);
        currentRepository.saveAndFlush(current);
    }

    // DELETE a current 'book' by current id
    @Override
    @Transactional
    public void deleteCurrentById(Long currentId){
        Optional<Current> currentOptional = currentRepository.findById(currentId);
        currentOptional.ifPresent(current -> currentRepository.delete(current));
    }

    // UPDATE/PUT a current 'book' by current id
    @Override
    @Transactional
    public void updateCurrentById(CurrentDto currentDto){
        Optional<Current> currentOptional = currentRepository.findById(currentDto.getCurrent_id());
        currentOptional.ifPresent( current -> {
            current.setCurrentPage(currentDto.getCurrentPage());
            current.setTotalPages(current.getTotalPages());
            currentRepository.saveAndFlush(current);
        });
    }

    // GET all current 'books' by user id
    @Override
    public List<CurrentDto> getAllCurrentByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            List<Current> currentList = currentRepository.findAllByUserEquals(userOptional.get());
            return currentList.stream().map(current -> new CurrentDto(current)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
