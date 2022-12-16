package org.example.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

//@Entity
//@Table(name="Bookshelf")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Bookshelf {

//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    private Long bookshelf_id;

//    @ManyToOne                // creates the association within Hibernate
//    @JsonBackReference        // prevents infinite recursion when you deliver the resource up as JSON through the REST API endpoint you will create
//    private User user;

//    @ManyToMany(mappedBy="bookshelfSet")               // creates the association within Hibernate
//    private Set<Book> book;

}
