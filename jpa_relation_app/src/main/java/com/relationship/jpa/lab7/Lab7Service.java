package com.relationship.jpa.lab7;
import com.relationship.jpa.lab2.Author;
import com.relationship.jpa.lab2.AuthorRepository;
import com.relationship.jpa.lab2.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@Service
class Lab7Service {
    private final AuthorRepository authors;
    Lab7Service(AuthorRepository authors) {
        this.authors = authors;
    }
    /** orphanRemoval: the book is only removed from the list, yet its row is deleted. */
    @Transactional
    public void removeBook(Long authorId, Long bookId) {
        Author author = authors.findById(authorId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Author " + authorId + " not found"));
        Book book = author.getBooks().stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Book " + bookId + " does not belong to author " + authorId));
        author.removeBook(book);
    }
    /** cascade REMOVE: deleting the author deletes all its books first. */
    @Transactional
    public void deleteAuthor(Long authorId) {
        authors.deleteById(authorId);
    }
}
