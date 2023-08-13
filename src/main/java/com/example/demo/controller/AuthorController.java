package com.example.demo.controller;

import com.example.demo.dto.AuthorDto;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<?> CreateAuthor(@RequestBody AuthorDto authorDto) {
        val author = authorService.CreateAuthor(authorDto);
        return ResponseEntity.status(HttpStatus.OK).body(author);
    }
    @GetMapping
    public ResponseEntity<?> getAllAuthors() {
        val authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        val author = authorService.getAuthorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(author);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<?> UpdateAuthorById(@RequestBody AuthorDto authorDto,@PathVariable Long id) {
        val author = authorService.Update(authorDto,id);
        return ResponseEntity.status(HttpStatus.OK).body(author);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
