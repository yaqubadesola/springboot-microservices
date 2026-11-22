package com.relationship.jpa.lab7;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lab7")
public class Lab7Controller {
    private final Lab7Service service;
    Lab7Controller(Lab7Service service) {
        this.service = service;
    }
    @DeleteMapping("/authors/{authorId}/books/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBook(@PathVariable Long authorId, @PathVariable Long bookId) {
        service.removeBook(authorId, bookId);
    }
    @DeleteMapping("/authors/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long authorId) {
        service.deleteAuthor(authorId);
    }
}