package com.ggproject.myBookshelf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookSearchResponseDto {

    @JsonProperty("documents")
    private List<BookSearchDocumentDto> documents = new ArrayList<>();
}