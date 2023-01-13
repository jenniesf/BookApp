package org.example.services;

import org.example.dtos.BookDto;
import org.example.dtos.CurrentDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CurrentService {
    // ADD POST a current 'book' by user id
    @Transactional
    void addCurrent(CurrentDto currentDto, Long userId, Long bookId);

    // DELETE a current 'book' by current id
    @Transactional
    void deleteCurrentById(Long currentId);

    // UPDATE/PUT a current 'book' by current id
    @Transactional
    void updateCurrentById(CurrentDto currentDto);

    // GET all current 'books' by user id
    List<CurrentDto> getAllCurrentByUserId(Long userId);

    // find current by current id
    Optional<CurrentDto> getCurrentById(Long currentId);
}
