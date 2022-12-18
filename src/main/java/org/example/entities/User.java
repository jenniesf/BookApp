package org.example.entities;

// @Entity - this is annotation is what tells Spring that this class is being mapped to a data source
// @Table - this is where you can set what table you want these objects to be mapped to

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dtos.UserDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private String firstname;
    @Column
    private String lastname;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference       // mitigate infinite recursion
    private Set<Book> bookSet = new HashSet<>();

    public User(UserDto userDto) {
        if(userDto.getUsername() != null) {
            this.username = userDto.getUsername();
        }
        if(userDto.getPassword() != null) {
            this.password = userDto.getPassword();
        }
        if(userDto.getFirstname() != null) {
            this.firstname = userDto.getFirstname();
        }
        if(userDto.getLastname() != null) {
            this.lastname = userDto.getLastname();
        }
    }

    //    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinTable(
//            name="user_book",
//            joinColumns={@JoinColumn(name="user_id")},
//            inverseJoinColumns={@JoinColumn(name="book_id")})
//    private Set<Book> bookSet;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JsonBackReference       // mitigate infinite recursion
//    private Set<Bookshelf> bookshelfSet = new HashSet<>();
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JsonBackReference       // mitigate infinite recursion
//    private Set<Review> reviewSet = new HashSet<>();

}
