package com.ggproject.myBookshelf.dto;

import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class BookListResponseDto {

    private Long id;
    private String name;
    private String isbn;
    private String author;
    private ReadStatus readStatus;
    private LocalDateTime readStart;
    private LocalDateTime readEnd;
    private String memo;
    private LocalDateTime createdDate;

    public BookListResponseDto(Book entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.isbn = entity.getIsbn();
        this.author = entity.getAuthor();
        this.readStatus = entity.getReadStatus();
        this.readStart = entity.getReadStart();
        this.readEnd = entity.getReadEnd();
        this.createdDate = entity.getCreatedDate();
    }
}
