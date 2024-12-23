package com.alura.gutendex_springboot.app;

import com.alura.gutendex_springboot.app.enums.Language;
import com.alura.gutendex_springboot.app.model.Author;
import com.alura.gutendex_springboot.app.model.Book;
import com.alura.gutendex_springboot.app.records.AuthorDto;
import com.alura.gutendex_springboot.app.records.BookDto;
import com.alura.gutendex_springboot.app.records.Results;
import com.alura.gutendex_springboot.app.repository.AuthorRepository;
import com.alura.gutendex_springboot.app.repository.BookRepository;
import com.alura.gutendex_springboot.app.http.Http;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class AppService {
    protected final Scanner input = new Scanner(System.in);
    private final ObjectMapper serialize = new ObjectMapper();
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;


    protected void findBookByTitle() throws JsonProcessingException {
        System.out.println("Enter the book title you want to find");
        var title = input.nextLine();
        var response = Http.load(title);
        var books = serialize.readValue(response, Results.class).books();

        if(books.isEmpty()) {
            System.out.println("Book not found\n");
            return;
        }

        BookDto book = books.get(0);
        AuthorDto author = book.authors().get(0);

        var authorRegistered = authorRepository.findByNameIgnoreCase(author.name());
        var bookRegistered = bookRepository.findByTitleIgnoreCase(book.title());

        var newBook = new Book(book.title(), book.languages().get(0), book.downloads());
        var newAuthor = new Author(List.of(newBook), author.deathYear(),author.birthYear(), author.name());


        if(bookRegistered.isPresent()){
            System.out.println("This book is already registered\n");
        } else if (authorRegistered.isPresent()) {
            newBook.setAuthor(authorRegistered.get());
            System.out.println(newBook);
            bookRepository.save(newBook);
        } else {
            System.out.println(newBook);
            authorRepository.save(newAuthor);
        }
    }

    protected void listBooks() {
        var books = bookRepository.findAll();
        books.forEach(System.out::println);
        var stats = books.stream().collect(Collectors.summarizingDouble(Book::getDownloads));
        System.out.println(stats.getMax());
        System.out.println(stats.getMin());
        System.out.println(stats.getAverage());
        System.out.println(stats.getSum());
        System.out.println(stats.getCount());
    }

    protected void listAuthors() {
        var authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }

    protected void listAuthorsByYear() {
        System.out.println("Enter the year of the author");
        try {
            int year = Integer.parseInt(input.nextLine());
            var authors = authorRepository.findAuthorsByYear(year);

            if(authors.isEmpty()){
                System.out.println("Authors in that year were not found\n");
            } else {
                authors.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid year\n");
        }
    }

    protected void listBooksByLanguage() {
        var languages = """
                Choose a language by its number:
                1- English
                2- Spanish
                3- Portuguese
                4- French
                """;
        System.out.println(languages);
        var option = input.nextLine();
        Language language;

        switch (option) {
            case "1":
                language = Language.ENGLISH;
                break;
            case "2":
                language = Language.SPANISH;
                break;
            case "3":
                language = Language.PORTUGUESE;
                break;
            case "4":
                language = Language.FRENCH;
                break;
            default:
                System.out.println("Enter a valid option");
                return;
        }

        var books = bookRepository.findAllByLanguage(language);

        if(books.isEmpty()) System.out.println("Books not found\n");
        else books.forEach(System.out::println);
    }

    protected void findAuthorByName() {
        System.out.println("Enter author name: ");
        var name = input.nextLine();
        var author = authorRepository.findAuthorByName(name);

        if(author.isEmpty()){
            System.out.println("Author not found\n");
        } else {
            System.out.println(author.get());
        }
    }

    protected void listTop10DownloadedBooks() {
        var books = bookRepository.getTop10ByOrderByDownloadsDesc();
        books.forEach(System.out::println);
    }

    protected void downloadedBookStatistics() {
        var books = bookRepository.findAll();
        var stats = books.stream().collect(Collectors.summarizingDouble(Book::getDownloads));

        System.out.println("Max downloads: " + stats.getMax());
        System.out.println("Min downloads: " + stats.getMin());
        System.out.println("Total downloads: " + stats.getSum() + "\n");
    }
}
