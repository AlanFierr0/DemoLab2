package com.example.demo.controller;

import com.example.demo.dto.AuthorDto;
import com.example.demo.generator.AuthorGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class AuthorControllerTest {
    @Autowired
    private TestRestTemplate TestrestTemplate;
    @BeforeEach
    public void setUp()  {
        List<AuthorDto> authorDtoList = AuthorGenerator.generateAuthors();
        for (AuthorDto authorDto : authorDtoList) {
            TestrestTemplate.postForEntity("/author", authorDto, String.class);
        }
    }


    @Test
    void getAllAuthorsTest() {
        ResponseEntity<?> response = TestrestTemplate.getForEntity("/author", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

    }

    @Test
    void getAuthorByIdTest() {
        ResponseEntity<?> response = TestrestTemplate.getForEntity("/author/1", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void createAuthorTest() {
        AuthorDto authorDto = AuthorGenerator.generateAuthor();
        ResponseEntity<?> response = TestrestTemplate.postForEntity("/author", authorDto, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateAuthorTest() {
        AuthorDto authorDto = AuthorGenerator.generateAuthor();
        ResponseEntity<?> response = TestrestTemplate.exchange("/author/1", HttpMethod.PUT, new HttpEntity<>(authorDto), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteAuthorTest() {
        ResponseEntity<?> response = TestrestTemplate.exchange("/author/1",HttpMethod.DELETE,null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}