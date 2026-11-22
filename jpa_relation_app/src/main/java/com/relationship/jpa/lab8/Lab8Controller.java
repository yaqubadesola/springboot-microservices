package com.relationship.jpa.lab8;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab8")
public class Lab8Controller {
    public record BookRow(Long id, String title, Long authorId) { }
    public record AuthorRow(Long id, String name) { }
    public record AuthorCount(String authorName, Long bookCount) { }
    public record BookWithAuthor(String title, String authorName) { }
    public record NewBook(String title) { }
    private final Lab8Service service;
    Lab8Controller(Lab8Service service) {
        this.service = service;
    }
    @PostMapping("/books/without-author")
    @ResponseStatus(HttpStatus.CREATED)
    public BookRow withoutAuthor(@RequestBody NewBook request) {
        return service.createBookWithoutAuthor(request.title());
    }
    @GetMapping("/books")
    public List<BookRow> byAuthor(@RequestParam String authorName) {
        return service.booksByAuthorName(authorName);
    }
       @GetMapping("/books/search")
    public List<BookRow> search(@RequestParam String title) {
        return service.booksWithTitleContaining(title);
    }
    @GetMapping("/books/no-author")
    public List<BookRow> noAuthor() {
        return service.booksWithoutAuthor();
    }
    @GetMapping("/books/with-author")
    public BookWithAuthor withAuthor(@RequestParam String title) {
        return service.bookWithAuthor(title);
    }
    @GetMapping("/authors/search")
    public List<AuthorRow> authorsSearch(@RequestParam String title) {
        return service.authorsWithTitleContaining(title);
    }
    @GetMapping("/authors/no-books")
    public List<AuthorRow> noBooks() {
        return service.authorsWithoutBooks();
    }
    @GetMapping("/authors/stats")
    public List<AuthorCount> stats() {
        return service.bookCounts();
    }
}