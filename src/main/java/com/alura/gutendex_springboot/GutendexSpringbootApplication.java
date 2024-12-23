package com.alura.gutendex_springboot;

import com.alura.gutendex_springboot.app.App;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GutendexSpringbootApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(GutendexSpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var app = new App();
        app.start();
    }
}
