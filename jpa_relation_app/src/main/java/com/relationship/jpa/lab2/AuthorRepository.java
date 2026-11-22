package com.relationship.jpa.lab2;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // ---- added in Lab 6 ---
    @Query("select distinct a from Author a left join fetch a.books")
    List<Author> findAllWithBooks();
    @EntityGraph(attributePaths = "books")
    @Query("select a from Author a")
    List<Author> findAllWithBooksGraph();
    // ---- added in Lab 8 ---
    List<Author> findByBooksTitleContainingIgnoreCase(String fragment);
    @Query("select a from Author a where a.books is empty")
    List<Author> findAuthorsWithoutBooks();
    @Query("select a.name as authorName, count(b) as bookCount "
            + "from Author a left join a.books b group by a.name order by a.name")
    List<AuthorStats> bookCountPerAuthor();
}
