package org.example.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dtos.CurrentDto;

import javax.persistence.*;

@Entity
@Table(name="Current")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Current {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long current_id;
    @Column
    private Integer currentPage;
    @Column
    private Integer totalPages;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToOne
    @JsonBackReference
    private Book book;

    public Current(CurrentDto currentDto) {
        if(currentDto.getCurrentPage() != null) {
            this.currentPage = currentDto.getCurrentPage();
        }
        if(currentDto.getTotalPages() != null) {
            this.totalPages = currentDto.getTotalPages();
        }
    }
}
