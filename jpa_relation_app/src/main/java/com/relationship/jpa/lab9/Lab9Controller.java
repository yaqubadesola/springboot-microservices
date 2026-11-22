package com.relationship.jpa.lab9;

import com.relationship.jpa.lab2.Lab2Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Lab9Controller {
    public record RenameRequest(String name) { }
    private final Lab9Service service;
    Lab9Controller(Lab9Service service) {
        this.service = service;
    }
    @PutMapping("/authors/{id}")
    public Lab2Controller.AuthorDto rename(@PathVariable Long id, @RequestBody RenameRequest request) {
        return service.rename(id, request);
    }
    @PutMapping("/books/{bookId}/author/{authorId}")
    public Lab2Controller.BookDto moveBook(@PathVariable Long bookId, @PathVariable Long authorId) {
        return service.moveBook(bookId, authorId);
    }
}