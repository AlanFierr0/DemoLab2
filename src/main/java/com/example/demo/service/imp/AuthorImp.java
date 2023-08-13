package com.example.demo.service.imp;

import com.example.demo.dto.AuthorDto;
import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepo;
import com.example.demo.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Validated
public class AuthorImp implements AuthorService {

    private final AuthorRepo authorRepo;

    public AuthorImp(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author CreateAuthor(AuthorDto AuthorDto) {
        Author author = Author.builder()
                .name(AuthorDto.getName())
                .id(AuthorDto.getId())
                .build();
        return authorRepo.save(author);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepo.findAll();
        List<AuthorDto> authorsDto = new ArrayList<>();
        for (Author author : authors) {
            AuthorDto authorDto = AuthorDto.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .build();
            authorsDto.add(authorDto);
        }
        return authorsDto;
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        return authorRepo.findById(id);
    }

    @Override
    public Optional<Author> Update(AuthorDto authorDto, Long id) {
        Optional<Author> author = authorRepo.findById(id);
        if(author.isPresent()) {
            author.get().setName(authorDto.getName());
            authorRepo.save(author.get());
        }
        return author;
    }

    @Override
    public void deleteAuthor(Long id) {
        if(authorRepo.existsById(id)) {
            authorRepo.deleteById(id);
        }
    }
}
