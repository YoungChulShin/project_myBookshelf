package com.ggproject.myBookshelf.dto;

import com.ggproject.myBookshelf.domain.ReadStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class BookSaveRequestDto {

    @NotEmpty(message = "책 이름은 필수 값입니다")
    private String bookName;

    @NotEmpty(message = "ISBN은 필수 값입니다")
    private String isbn;

    private String author;

    private ReadStatus readStatus;

    @Builder
    public BookSaveRequestDto(String bookName, String isbn, String author, ReadStatus readStatus) {
        this.bookName = bookName;
        this.isbn = isbn;
        this.author = author;
        this.readStatus = readStatus;
    }
}
