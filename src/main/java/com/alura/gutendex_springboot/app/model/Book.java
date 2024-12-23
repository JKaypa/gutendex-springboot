package com.alura.gutendex_springboot.app.model;

import com.alura.gutendex_springboot.app.enums.Language;
import jakarta.persistence.*;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @ManyToOne
    private Author author;

    @Enumerated(EnumType.STRING)
    private Language language;

    private int downloads;

    public Book(){}
    public Book(String title, String language, int downloads) {
        this.title = title;
        this.language = Language.fromString(language);
        this.downloads = downloads;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Language getLanguage() {
        return language;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String toString () {
        return "----------- Book -----------\n" +
                "Title: " + title + "\n" +
                "Author: " + author.getName() + "\n" +
                "Language: " +  language + "\n" +
                "Downloads: " +  downloads + "\n";
    }
}
