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
public class UsedBookSearchResponseDto {

    @JsonProperty("link")
    private String link;

    @JsonProperty("query")
    private String query;

    @JsonProperty("version")
    private String version;

    @JsonProperty("itemOffStoreList")
    private List<UsedBookSearchStoreInfoDto> storeList = new ArrayList<>();
}
