package com.alura.gutendex_springboot.app.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "birth_year")
    private int birthYear;

    @Column(name = "death_year")
    private int deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public Author(){}
    public Author(List<Book> books, int deathYear, int birthYear, String name) {
        books.forEach(book -> book.setAuthor(this));
        this.books = books;
        this.deathYear = deathYear;
        this.birthYear = birthYear;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "\n---------- Author -----------\n" +
                "Name: " + name + "\n" +
                "Birth year: " + birthYear + "\n" +
                "Death year: " + deathYear + "\n" +
                "Books : " + getBooks()
                .stream()
                .map(Book::getTitle)
                .toList() + "\n";
    }
}
