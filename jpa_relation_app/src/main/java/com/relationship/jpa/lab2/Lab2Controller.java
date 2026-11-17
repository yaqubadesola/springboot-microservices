package com.relationship.jpa.lab2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Lab2Controller {
    public record BookDto(Long id, String title, Long authorId) { }
    public record AuthorDto(Long id, String name, List<BookDto> books) { }
    public record CreateAuthorRequest(String name, List<String> bookTitles) { }
    public record CreateBookRequest(String title) { }
    private final AuthorService service;
    Lab2Controller(AuthorService service) {
        this.service = service;
    }
    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto create(@RequestBody CreateAuthorRequest request) {
        return service.create(request);
    }

    @GetMapping("/authors")
    public List<AuthorDto> authors() {
        return service.findAll();
    }
    @GetMapping("/authors/{id}")
    public AuthorDto author(@PathVariable Long id) {
        return service.findById(id);
    }
    @PostMapping("/authors/{id}/books")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto addBook(@PathVariable Long id, @RequestBody CreateBookRequest request) {
        return service.addBook(id, request);
    }
    @PostMapping("/authors/{id}/books/wrong")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto addBookWrong(@PathVariable Long id, @RequestBody CreateBookRequest request) {
        return service.addBookWrong(id, request);
    }
    @GetMapping("/books")
    public List<BookDto> books() {
        return service.allBooks();
    }
}