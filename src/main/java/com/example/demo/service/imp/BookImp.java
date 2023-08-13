package com.example.demo.service.imp;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.repository.AuthorRepo;
import com.example.demo.repository.BookRepo;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated

public class BookImp implements BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public BookImp(BookRepo bookRepo, AuthorRepo authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    @Override
    public Book CreateBook(BookDto bookDto) {
        Optional<Author> author = authorRepo.findById(bookDto.getAuthor().getId());
        if (author.isEmpty()) {
            Author newAuthor = Author.builder()
                    .name(bookDto.getAuthor().getName())
                    .build();
            Book book = Book.builder()
                    .title(bookDto.getTitle())
                    .publicationYear(bookDto.getYear())
                    .genre(bookDto.getGenre())
                    .author(newAuthor)
                    .build();
            return bookRepo.save(book);
        }
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .publicationYear(bookDto.getYear())
                .genre(bookDto.getGenre())
                .author(author.get())
                .build();
        return bookRepo.save(book);

    }

    @Override
    public List<BookDto> getAllBooks(boolean complete) {
        List<Book> books = bookRepo.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        if (complete) {
            for (Book book : books) {
                BookDto bookDto = BookDto.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .year(book.getPublicationYear())
                        .genre(book.getGenre())
                        .author(AuthorDto.builder()
                                .id(book.getAuthor().getId())
                                .name(book.getAuthor().getName())
                                .build())
                        .build();
                bookDtos.add(bookDto);
            }
        } else {
            for (Book book : books) {
                BookDto bookDto = BookDto.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .year(book.getPublicationYear())
                        .genre(book.getGenre())
                        .author(AuthorDto.builder()
                                .id(book.getAuthor().getId())
                                .build())
                        .build();
                bookDtos.add(bookDto);
            }
        }
        return bookDtos;
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepo.findById(id);
    }

    @Override
    public Optional<Book> Update(BookDto bookDto, Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            Author author = authorRepo.findById(bookDto.getAuthor().getId()).get();
            book.get().setTitle(bookDto.getTitle());
            book.get().setAuthor(author);
            bookRepo.save(book.get());
        }
        return book;
    }

    @Override
    public void deleteBook(Long id) {
        if (bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
        }
    }
}