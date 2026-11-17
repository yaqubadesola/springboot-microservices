package com.relationship.jpa.lab2;

import jakarta.persistence.*;
@Entity
@Table(name = "lab2_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    // OWNING side: this is the side that writes author_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    protected Book() { }
    public Book(String title) {
        this.title = title;
    }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
    @Override
    public String toString() {
        return "Book{id=" + id + ", title=" + title + "}";
    }
}