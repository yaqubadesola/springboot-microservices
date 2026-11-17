package com.relationship.jpa.lab2;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "lab2_author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // INVERSE side: no foreign key here. "author" is the field name inside Book.
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();
    protected Author() { }
    public Author(String name) {
        this.name = name;
    }
    // Helper methods keep BOTH sides of the relationship in sync
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }
    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Book> getBooks() { return books; }
    @Override
    public String toString() {
        return "Author{id=" + id + ", name=" + name + "}";
    }
}
