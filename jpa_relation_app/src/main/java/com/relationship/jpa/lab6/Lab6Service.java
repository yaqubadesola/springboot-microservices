package com.relationship.jpa.lab6;

import com.relationship.jpa.lab2.Author;
import com.relationship.jpa.lab2.AuthorRepository;
import com.relationship.jpa.lab2.Book;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.LazyInitializationException;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
class Lab6Service {
    private final AuthorRepository authors;
    private final EntityManagerFactory emf;
    Lab6Service(AuthorRepository authors, EntityManagerFactory emf) {
        this.authors = authors;
        this.emf = emf;
    }
    private Statistics stats() {
        return emf.unwrap(SessionFactory.class).getStatistics();
    }
    /** Adds 3 authors with 2 books each. Call it several times to make N bigger. */
    public long seed() {
        for (String name : List.of("Achebe", "Soyinka", "Adichie")) {
            Author author = new Author(name);
            author.addBook(new Book(name + " - Book 1"));
            author.addBook(new Book(name + " - Book 2"));
            authors.save(author);
        }
        return authors.count();
    }
    /** NO @Transactional on purpose: the lazy collection is touched with no session. */
    public String lazyError() {
        List<Author> all = authors.findAll();
        if (all.isEmpty()) {
            return "No authors yet. Call POST /api/lab6/seed first.";
        }
        try {
            return "Loaded " + all.get(0).getBooks().size() + " books (unexpected)";
        }
        catch (LazyInitializationException e) {
            return "LazyInitializationException: " + e.getMessage();
        }
    }
    @Transactional(readOnly = true)
    public Lab6Controller.FetchReport nPlusOne() {
        return measure("N+1: findAll() then lazy books", authors::findAll);
    }
    @Transactional(readOnly = true)
    public Lab6Controller.FetchReport joinFetch() {
        return measure("JOIN FETCH", authors::findAllWithBooks);
    }
    @Transactional(readOnly = true)
    public Lab6Controller.FetchReport entityGraph() {
        return measure("@EntityGraph", authors::findAllWithBooksGraph);
    }
    private Lab6Controller.FetchReport measure(String label, Supplier<List<Author>> query) {
        stats().clear();
        List<Author> all = query.get();
        int books = 0;
        for (Author a : all) {
            books += a.getBooks().size();          // lazy: may fire one SELECT per author
        }
        return new Lab6Controller.FetchReport(label, all.size(), books,
                stats().getPrepareStatementCount());
    }
}