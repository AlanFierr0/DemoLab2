package com.example.demo.generator;




import com.example.demo.dto.AuthorDto;

import java.util.ArrayList;
import java.util.List;

public class AuthorGenerator {
    public static List<AuthorDto> generateAuthors(){
        List<AuthorDto> authors = new ArrayList<>();
        authors.add(AuthorDto.builder()
                .id(1L)
                .name("Author1")
                .build());
        authors.add(AuthorDto.builder()
                .id(2L)
                .name("Author2")
                .build());
        authors.add(AuthorDto.builder()
                .id(3L)
                .name("Author3")
                .build());
        authors.add(AuthorDto.builder()
                .id(4L)
                .name("Author4")
                .build());
        authors.add(AuthorDto.builder()
                .id(5L)
                .name("Author5")
                .build());
        return authors;
    }
    public static AuthorDto generateAuthor(){
        return AuthorDto.builder()
                .id(1L)
                .name("Author1")
                .build();
    }
}
