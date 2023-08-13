package com.example.demo.model;


import com.example.demo.dto.AuthorDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
    private List<Book> books;



}
