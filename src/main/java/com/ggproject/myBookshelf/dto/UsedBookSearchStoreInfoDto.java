package com.ggproject.myBookshelf.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsedBookSearchStoreInfoDto {

    @JsonProperty("offCode")
    private String offCode;

    @JsonProperty("offName")
    private String offName;

    @JsonProperty("link")
    private String link;
}