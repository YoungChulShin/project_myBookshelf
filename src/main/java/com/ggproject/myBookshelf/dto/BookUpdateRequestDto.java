package com.ggproject.myBookshelf.dto;

import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BookUpdateRequestDto {

    private Long id;
    private String name;
    private String isbn;
    private String author;
    private ReadStatus readStatus;
    private LocalDateTime readStart;
    private LocalDateTime readEnd;
    private String summaryLink;
    private String memo;

//    @Builder
//    public BookUpdateRequestDto(ReadStatus readStatus,
//                                LocalDateTime readStart,
//                                LocalDateTime readEnd,
//                                String summaryLink,
//                                String memo) {
//        this.readStatus = readStatus;
//        this.readStart = readStart;
//        this.readEnd = readEnd;
//        this.summaryLink = summaryLink;
//        this.memo = memo;
//    }

    public BookUpdateRequestDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.isbn = book.getIsbn();
        this.author = book.getAuthor();
        this.readStatus = book.getReadStatus();
        this.readStart = book.getReadStart();
        this.readEnd = book.getReadEnd();
        this.summaryLink = book.getSummaryLink();
        this.memo = book.getMemo();
    }
}
