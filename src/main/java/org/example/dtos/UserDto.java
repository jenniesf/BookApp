package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long user_id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Set<BookDto> bookDtoSet = new HashSet<>();

    public UserDto(User user) {
        if(user.getUser_id() != null) {
            this.user_id = user.getUser_id();
        }
        if(user.getUsername() != null) {
            this.username = user.getUsername();
        }
        if(user.getPassword() != null){
            this.password = user.getPassword();
        }
        if(user.getFirstname() != null){
            this.firstname = user.getFirstname();
        }
        if(user.getLastname() != null){
            this.lastname = user.getLastname();
        }
    }

}
