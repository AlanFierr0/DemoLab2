package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<?> CreateBook(@RequestBody BookDto bookDto) {
        val book = bookService.CreateBook(bookDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks(@RequestParam(value = "completed") Boolean completed){
        List<BookDto> books = bookService.getAllBooks(completed);
        return ResponseEntity.ok(books);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        val book = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<?> UpdateBookById(@RequestBody BookDto bookDto,@PathVariable Long id) {
        val book = bookService.Update(bookDto,id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }





}
