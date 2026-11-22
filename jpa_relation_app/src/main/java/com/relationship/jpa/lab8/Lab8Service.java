package com.relationship.jpa.lab8;

import com.relationship.jpa.lab2.Author;
import com.relationship.jpa.lab2.AuthorRepository;
import com.relationship.jpa.lab2.Book;
import com.relationship.jpa.lab2.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
class Lab8Service {
    private final AuthorRepository authors;
    private final BookRepository books;
    Lab8Service(AuthorRepository authors, BookRepository books) {
        this.authors = authors;
        this.books = books;
    }
    @Transactional
    public Lab8Controller.BookRow createBookWithoutAuthor(String title) {
        return row(books.save(new Book(title)));
    }
    @Transactional(readOnly = true)
    public List<Lab8Controller.BookRow> booksByAuthorName(String authorName) {
        return books.findByAuthorName(authorName).stream().map(this::row).toList();
    }
    @Transactional(readOnly = true)
    public List<Lab8Controller.BookRow> booksWithTitleContaining(String fragment) {
        return books.findByTitleContainingIgnoreCase(fragment).stream().map(this::row).toList();
    }
    @Transactional(readOnly = true)
    public List<Lab8Controller.BookRow> booksWithoutAuthor() {
        return books.findByAuthorIsNull().stream().map(this::row).toList();
    }
    @Transactional(readOnly = true)
    public List<Lab8Controller.AuthorRow> authorsWithTitleContaining(String fragment) {
        return authors.findByBooksTitleContainingIgnoreCase(fragment).stream()
                .map(this::authorRow).distinct().toList();
    }
    @Transactional(readOnly = true)
    public List<Lab8Controller.AuthorRow> authorsWithoutBooks() {
        return authors.findAuthorsWithoutBooks().stream().map(this::authorRow).toList();
    }
    @Transactional(readOnly = true)
    public List<Lab8Controller.AuthorCount> bookCounts() {
        return authors.bookCountPerAuthor().stream()
                .map(s -> new Lab8Controller.AuthorCount(s.getAuthorName(), s.getBookCount()))
                .toList();
    }
    @Transactional(readOnly = true)
    public Lab8Controller.BookWithAuthor bookWithAuthor(String title) {
        return books.findByTitleWithAuthor(title)
                .map(b -> new Lab8Controller.BookWithAuthor(
                        b.getTitle(), b.getAuthor().getName()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No book with an author titled " + title));
    }
    private Lab8Controller.BookRow row(Book b) {
        Long authorId = b.getAuthor() == null ? null : b.getAuthor().getId();
        return new Lab8Controller.BookRow(b.getId(), b.getTitle(), authorId);
    }
    private Lab8Controller.AuthorRow authorRow(Author a) {
        return new Lab8Controller.AuthorRow(a.getId(), a.getName());
    }
}