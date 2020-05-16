package com.ggproject.myBookshelf.service.api;

import com.ggproject.myBookshelf.dto.UsedBookSearchResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AladinBookSerachService {

    private final RestTemplate restTemplate;

    @Value("${api-property.aladin-book-search.api-url}")
    private String searchUrl;

    @Value("${api-property.aladin-book-search.api-key}")
    private String searchKey;

    public AladinBookSerachService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public UsedBookSearchResponseDto getBookInformations(String isbn) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(searchUrl)
                .queryParam("itemIdType", "ISBN13")
                .queryParam("ItemId", isbn)
                .queryParam("output", "js")
                .queryParam("ttbkey", searchKey);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        return  restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, UsedBookSearchResponseDto.class).getBody();
    }
}
