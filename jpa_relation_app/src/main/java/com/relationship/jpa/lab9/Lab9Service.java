package com.relationship.jpa.lab9;

import com.relationship.jpa.lab2.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Service
class Lab9Service {
    private final AuthorRepository authors;
    private final BookRepository books;
    Lab9Service(AuthorRepository authors, BookRepository books) {
        this.authors = authors;
        this.books = books;
    }
    @Transactional
    public Lab2Controller.AuthorDto rename(Long authorId, Lab9Controller.RenameRequest request) {
        Author author = author(authorId);
        author.setName(request.name());
// dirty checking: UPDATE at commit
        return toDto(author);
    }
    /**
     * Changing a relationship = changing the OWNING side (book.author).
     * We do NOT remove the book from the old author's list: with orphanRemoval
     * that would delete the book instead of moving it.
     */
    @Transactional
    public Lab2Controller.BookDto moveBook(Long bookId, Long newAuthorId) {
        Book book = books.findById(bookId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Book " + bookId + " not found"));
        Author target = author(newAuthorId);
        target.addBook(book);
        authors.flush();
        return new Lab2Controller.BookDto(book.getId(), book.getTitle(), book.getAuthor().getId());
    }
    private Author author(Long id) {
        return authors.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Author " + id + " not found"));
    }
    private Lab2Controller.AuthorDto toDto(Author a) {
        return new Lab2Controller.AuthorDto(a.getId(), a.getName(), a.getBooks().stream()
                .map(b -> new Lab2Controller.BookDto(b.getId(), b.getTitle(), a.getId()))
                .collect(Collectors.toList()));
    }
}