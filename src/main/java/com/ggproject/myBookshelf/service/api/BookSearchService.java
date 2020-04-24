package com.ggproject.myBookshelf.service.api;

import com.ggproject.myBookshelf.dto.BookSearchResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Service
public class BookSearchService {

    private final RestTemplate restTemplate;

    private String searchApiUrl = "https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-book";
    private String searchApiKey = "f0f80f2a0ac352cfbde3386f3ac694cd";

    public BookSearchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BookSearchResponseDto getBookInformations(String searchItem) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(searchApiUrl)
                .queryParam("query", searchItem);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.AUTHORIZATION, searchApiKey);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        return  restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, BookSearchResponseDto.class).getBody();
    }
}
