package com.example.resttemplate;

import com.example.resttemplate.model.BookDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.client.RestTemplate;

public class RestTemplateDemo implements CommandLineRunner {
    private final RestTemplate restTemplate;

    public RestTemplateDemo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        var allBooksResponse = this.restTemplate
                .getForEntity("http://localhost:8080/api/books", BookDTO[].class);

        if (allBooksResponse.hasBody()) {
            var allBooks = allBooksResponse.getBody();

            for (var bookDto : allBooks) {
                System.out.println("All books " + bookDto);
            }
        }
    }
}
