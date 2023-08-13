package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer publicationYear;
    private String genre;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;
}
