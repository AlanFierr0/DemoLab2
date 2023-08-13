package com.example.demo.generator;



import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;

import java.util.ArrayList;
import java.util.List;

public class BookGenerator {
    public List<BookDto> generateBooks(){
        List<BookDto> books = new ArrayList<>();

        books.add(BookDto.builder().id(1L).title("Book1").year(2000).genre("genre1")
                .author(AuthorDto.builder()
                        .id(6L)
                        .name("Author6")
                        .build())
                .build());
        books.add(BookDto.builder().id(2L).title("Book2").year(2001).genre("genre2")
                .author(AuthorDto.builder()
                        .id(7L)
                        .name("Author7")
                        .build())
                .build());
        books.add(BookDto.builder().id(3L).title("Book3").year(2002).genre("genre3")
                .author(AuthorDto.builder()
                        .id(8L)
                        .name("Author8")
                        .build())
                .build());
        books.add(BookDto.builder().id(4L).title("Book4").year(2003).genre("genre4")
                .author(AuthorDto.builder()
                        .id(9L)
                        .name("Author9")
                        .build())
                .build());
        books.add(BookDto.builder().id(5L).title("Book5").year(2004).genre("genre5")
                .author(AuthorDto.builder()
                        .id(10L)
                        .name("Author10")
                        .build())
                .build());

        return books;

    }

    public static BookDto generateBook(){
        return BookDto.builder().id(1L).title("Book1").year(2000).genre("genre1")
                .author(AuthorDto.builder()
                        .id(6L)
                        .name("Author6")
                        .build())
                .build();
    }
}
