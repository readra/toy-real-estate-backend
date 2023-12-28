package com.toy.realestatebackend.etc;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

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

        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory();
        defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        String realEstate = WebClient.builder()
                .uriBuilderFactory(defaultUriBuilderFactory)
                .build()
                .get()
                .uri("https://api.odcloud.kr/api/HousePriceTrendSvc/v1/getHousePriceIndex?region_cd=11000&research_date=202202&apt_type=0&tr_gbn=T&serviceKey=%2F7MeSbybd07ucEmj8BF72GmhsZV9KbqQ2BTpylshbKDKGNzSktYgCYvTOkvKuZCxWc8WHA5B3ecQ9qld7%2BGjOw%3D%3D")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("REAL-ESTATE]" + realEstate);

        System.out.println("WebFlux Test Completed.");
    }
}
