package com.alura.gutendex_springboot.app.repository;

import com.alura.gutendex_springboot.app.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameIgnoreCase(String name);

    @Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND a.deathYear >= :year")
    List<Author> findAuthorsByYear(int year);

    @Query("SELECT a FROM Author a WHERE a.name ILIKE %:name%")
    Optional<Author> findAuthorByName(String name);
}
