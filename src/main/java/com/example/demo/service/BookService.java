package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book CreateBook(BookDto bookDto);
    List<BookDto> getAllBooks(boolean complete);
    Optional<Book> getBookById(Long id);
    Optional<Book> Update(BookDto bookDto, Long id);
    void deleteBook(Long id);
}
