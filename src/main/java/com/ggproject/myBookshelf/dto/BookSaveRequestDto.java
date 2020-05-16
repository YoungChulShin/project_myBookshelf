package com.ggproject.myBookshelf.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Setter
@Getter
public class BookSaveRequestDto {

    @NotEmpty(message = "책 이름은 필수 값입니다")
    private String bookName;
    @NotEmpty(message = "ISBN은 필수 값입니다")
    private String isbn;
    private String author;
    private String infoLink;
}
