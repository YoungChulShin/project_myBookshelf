package com.ggproject.myBookshelf.service.api;

import com.ggproject.myBookshelf.dto.BookSearchResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;

@Service
public class BookSearchService {

    private final RestTemplate restTemplate;

    @Value("${api-property.booksearch.api-url}")
    private String searchApiUrl;
    @Value("${api-property.booksearch.api-key}")
    private String searchApiKey ;

    public BookSearchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BookSearchResponseDto getBookInformations(String searchItem) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(searchApiUrl)
                .queryParam("query", "스프링");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.AUTHORIZATION, searchApiKey);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, httpEntity, BookSearchResponseDto.class).getBody();
    }
}
