package com.toy.realestatebackend.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * RestTemplate 설정
 *
 * @author 김진용
 */
@Configuration
public class RestTemplateConfig {
    /**
     * RestTemplate 객체
     *
     * @param restTemplateBuilder
     *      RestTemplateBuilder 객체
     * @return
     *      RestTemplate 객체
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .requestFactory(
                        () -> new BufferingClientHttpRequestFactory(
                                new SimpleClientHttpRequestFactory()
                        )
                ).additionalMessageConverters(
                    new StringHttpMessageConverter(
                            StandardCharsets.UTF_8
                    )
                ).build();
    }
}
