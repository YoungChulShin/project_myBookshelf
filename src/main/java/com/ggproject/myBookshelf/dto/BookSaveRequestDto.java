package com.ggproject.myBookshelf.dto;

import com.ggproject.myBookshelf.domain.ReadStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSaveRequestDto {

    private String bookName;
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
