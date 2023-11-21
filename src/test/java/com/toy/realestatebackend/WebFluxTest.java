package com.toy.realestatebackend;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;

public class WebFluxTest {
    @Test
    void webFluxTest() {
        WebClient webClient = WebClient.builder().build();

        String willResponse = "[\"강서구\",\"강동구\"]";

        String response = webClient.get()
                .uri("http://localhost:8080/api/apartment")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("[ACTUAL]" + response);
        System.out.println("[EXPECTED]" + willResponse);
        assertThat(response).isEqualTo(willResponse);
        System.out.println("WebFlux Test Completed.");
    }
}
