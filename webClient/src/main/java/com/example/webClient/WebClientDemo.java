package com.example.webClient;

import com.example.webClient.model.UserDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientDemo implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        var webClient = WebClient.create("https://regres.in/");

        var user = webClient.get()
                .uri("api/users/2")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block();

        System.out.println(user);
    }
}
