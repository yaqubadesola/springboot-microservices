package com.relationship.jpa.lab2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
      // ---- added in Lab 8 ---
    List<Book> findByAuthorName(String authorName);
    List<Book> findByAuthorId(Long authorId);
    List<Book> findByTitleContainingIgnoreCase(String fragment);
    List<Book> findByAuthorIsNull();
    @Query("select b from Book b join fetch b.author where b.title = :title")
    Optional<Book> findByTitleWithAuthor(@Param("title") String title);
}