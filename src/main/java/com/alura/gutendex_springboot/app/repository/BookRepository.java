package com.alura.gutendex_springboot.app.repository;

import com.alura.gutendex_springboot.app.enums.Language;
import com.alura.gutendex_springboot.app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleIgnoreCase(String title);
    List<Book> findAllByLanguage(Language language);
    List<Book> getTop10ByOrderByDownloadsDesc();
}
