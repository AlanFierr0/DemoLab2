package com.example.demo.service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author CreateAuthor(AuthorDto authorDto);
    List<AuthorDto> getAllAuthors();
    Optional<Author> getAuthorById(Long id);
    Optional<Author> Update(AuthorDto authorDto, Long id);
    void deleteAuthor(Long id);
}
