package org.example.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
//@Table(name="Reviews")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Review {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long review_id;

//    @ManyToOne                // creates the association within Hibernate
//    @JsonBackReference        // prevents infinite recursion when you deliver the resource up as JSON through the REST API endpoint you will create
//    private User user;

//    @ManyToMany               // creates the association within Hibernate
//    @JsonBackReference        // prevents infinite recursion when you deliver the resource up as JSON through the REST API endpoint you will create
//    private Book book;

}
