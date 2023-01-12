package org.example.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.Book;
import org.example.entities.Current;
import org.example.entities.User;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentDto implements Serializable {

    private Long current_id;
    private Integer currentPage;
    private Integer totalPages;
    private UserDto userDto;
    private BookDto bookDto;

    public CurrentDto(Current current) {
        if(current.getCurrent_id() != null){
            this.current_id = current.getCurrent_id();
        }
        if(current.getCurrentPage() != null){
            this.currentPage = current.getCurrentPage();
        }
        if(current.getTotalPages() != null){
            this.totalPages = current.getTotalPages();
        }

        // set user dto data. UserDTO is passing in and out to client
        this.userDto = new UserDto();
        this.userDto.setFirstname(current.getUser().getFirstname());
        this.userDto.setUser_id((current.getUser().getUser_id()));

        // set book dto data
        this.bookDto = new BookDto();
        this.bookDto.setTitle(current.getBook().getTitle());
        this.bookDto.setBook_id(current.getBook().getBook_id());

    }
}
