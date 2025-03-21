package com.alura.gutendex_springboot.app.http;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Http {
    private static final String baseUrl = "https://gutendex.com/books/?search=";

    public static String load (String title) {

        var titleEncoded = URLEncoder.encode(title, StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(baseUrl + titleEncoded);
        HttpRequest request = HttpRequest.newBuilder(uri).build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            return "Error: " + e.getMessage();
        }
    }
}
