package com.ggproject.myBookshelf.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookSearchResponseDto {

    private String title;

    private List<String> authors = new ArrayList<>();

    private String isbn;

    private String thumbnail;

    private String url;

    private String status;
}
