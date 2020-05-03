package com.ggproject.myBookshelf.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookSearchDocumentDto {

    @JsonProperty("authors")
    List<String> authors = new ArrayList<>();

    @JsonProperty("contents")
    private String contents;

    @JsonProperty("datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "Asia/Seoul")
    private LocalDateTime datetime;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("price")
    private int price;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("sale_price")
    private int salePrice;

    @JsonProperty("status")
    private String status;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("title")
    private String title;

    @JsonProperty("translators")
    private List<String> translators = new ArrayList<>();

    @JsonProperty("url")
    private String url;
}
