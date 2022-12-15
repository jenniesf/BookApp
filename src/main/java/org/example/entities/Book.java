package org.example.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Books")
@Data
@AllArgsConstructor  // use Lombok to simplify code; no getter/setters/constructors
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;
    @Column
    private String title;
    @Column
    private String authors;                     // array or string?
    @Column(name = "published_date")
    private String publishedDate;
    @Column
    private String description;
    @Column
    private String smallThumbnail;
    @Column
    private String thumbnail;
    @Column(columnDefinition = "text")
    private String review;                      // user's book review

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="Bookshelf", joinColumns={@JoinColumn(name="bookshelf_id")}, inverseJoinColumns={@JoinColumn(name="book_id")})
    @JsonBackReference       // mitigate infinite recursion
    private Set<Bookshelf> bookshelfSet;

//    @ManyToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JsonBackReference       // mitigate infinite recursion
//    private Set<Review> reviewSet = new HashSet<>();

}
