package org.example.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dtos.BookDto;

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
    @Column
    private String published;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String smallThumbnail;
    @Column(columnDefinition = "TEXT")
    private String thumbnail;
    @Column
    private boolean bookshelf;
    @Column(columnDefinition = "text")
    private String review;                      // user's book review

    @ManyToOne                // creates the association within Hibernate
    @JsonBackReference        // prevents infinite recursion when you deliver the resource up as JSON through the REST API endpoint you will create
    private User user;

    public Book(BookDto bookDto) {
        if(bookDto.getTitle() != null) {
            this.title = bookDto.getTitle();
        }
        if(bookDto.getAuthors() != null) {
            this.authors = bookDto.getAuthors();
        }
        if(bookDto.getPublished() != null) {
            this.published = bookDto.getPublished();
        }
        if(bookDto.getDescription() != null) {
            this.description = bookDto.getDescription();
        }
        if(bookDto.getSmallThumbnail() != null) {
            this.smallThumbnail = bookDto.getSmallThumbnail();
        }
        if(bookDto.getThumbnail() != null) {
            this.thumbnail = bookDto.getThumbnail();
        }
        if(bookDto.isBookshelf()) {
            this.bookshelf = true;
        }
        if(bookDto.getReview() != null) {
            this.review = bookDto.getReview();
        }

    }

    //    @ManyToMany(mappedBy = "bookSet", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JsonBackReference       // mitigate infinite recursion
//    private Set<User> userSet;

}
