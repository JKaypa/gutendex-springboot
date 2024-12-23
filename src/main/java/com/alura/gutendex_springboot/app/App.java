package com.alura.gutendex_springboot.app;

import com.fasterxml.jackson.core.JsonProcessingException;

public class App extends AppService {
    public void start () throws JsonProcessingException {
        System.out.println("\n***** Welcome to Gutendex *****");
        String menu = """
                Choose an option through its number:
                1- Find book by title
                2- List registered books
                3- List registered authors
                4- List authors by year
                5- List books by language
                6- Find author by name
                7- List top 10 downloaded books
                8- Check downloaded book statistics
                0- Exit \n
                """;

        while(true){
            System.out.println(menu);
            var option = input.nextLine();

            switch (option) {
                case "1":
                    findBookByTitle();
                    break;
                case "2":
                    listBooks();
                    break;
                case "3":
                    listAuthors();
                    break;
                case "4":
                    listAuthorsByYear();
                    break;
                case "5":
                    listBooksByLanguage();
                    break;
                case "6":
                    findAuthorByName();
                    break;
                case "7":
                    listTop10DownloadedBooks();
                    break;
                case "8":
                    this.downloadedBookStatistics();
                    break;
                case "0":
                    System.out.println("Closing the app...");
                    return;
                default:
                    System.out.println("Please enter a valid number option");
                    break;
            }
        }
    }
}
