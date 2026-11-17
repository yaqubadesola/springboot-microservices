package com.relationship.jpa.lab2;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@Service
class AuthorService {
    private final AuthorRepository authors;
    private final BookRepository books;
    AuthorService(AuthorRepository authors, BookRepository books) {
        this.authors = authors;
        this.books = books;
    }
    @Transactional
    public Lab2Controller.AuthorDto create(Lab2Controller.CreateAuthorRequest request) {
        Author author = new Author(request.name());
        if (request.bookTitles() != null) {
            request.bookTitles().forEach(title -> author.addBook(new Book(title)));
        }
        return toDto(authors.save(author));
    }
    @Transactional(readOnly = true)
    public List<Lab2Controller.AuthorDto> findAll() {
        return authors.findAll().stream().map(this::toDto).toList();
    }
    @Transactional(readOnly = true)
    public Lab2Controller.AuthorDto findById(Long id) {
        return toDto(find(id));
    }
    /** RIGHT: the helper sets BOTH sides, so author_id is written. */
    @Transactional
    public Lab2Controller.AuthorDto addBook(Long authorId, Lab2Controller.CreateBookRequest req) {
        Author author = find(authorId);
        author.addBook(new Book(req.title()));
        authors.flush();
// flush so the new book gets its id
        return toDto(author);
    }
    /** WRONG on purpose: only the inverse side is updated, so author_id stays NULL. */
    @Transactional
    public Lab2Controller.BookDto addBookWrong(
            Long authorId, Lab2Controller.CreateBookRequest req) {
        Author author = find(authorId);
        Book book = new Book(req.title());
        author.getBooks().add(book);
        authors.flush();
        return toDto(book);
    }
    // book.author is never set!
    @Transactional(readOnly = true)
    public List<Lab2Controller.BookDto> allBooks() {
        return books.findAll().stream().map(this::toDto).toList();
    }
    private Author find(Long id) {
        return authors.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Author " + id + " not found"));
    }
    private Lab2Controller.BookDto toDto(Book b) {
        Long authorId = b.getAuthor() == null ? null : b.getAuthor().getId();
        return new Lab2Controller.BookDto(b.getId(), b.getTitle(), authorId);
    }
    private Lab2Controller.AuthorDto toDto(Author a) {
        List<Lab2Controller.BookDto> bookDtos = a.getBooks().stream().map(this::toDto).toList();
        return new Lab2Controller.AuthorDto(a.getId(), a.getName(), bookDtos);
    }
}