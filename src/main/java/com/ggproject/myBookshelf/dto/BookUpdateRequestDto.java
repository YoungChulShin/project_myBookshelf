package com.ggproject.myBookshelf.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class BookUpdateRequestDto {

    @NotEmpty(message = "ID공백일 수 없습니다.")
    private Long id;
    @NotEmpty(message = "책 이름은 필수 값입니다")
    private String name;
    @NotEmpty(message = "책 이름은 필수 값입니다")
    private String isbn;
    private String author;
    private ReadStatus prevReadStatus;

    private ReadStatus readStatus;

    private LocalDateTime readStart;
    private LocalDateTime readEnd;
    private String readStartString;
    private String readEndString;
    private String summaryLink;
    private String memo;

    public BookUpdateRequestDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.isbn = book.getIsbn();
        this.author = book.getAuthor();
        this.prevReadStatus = book.getReadStatus();
        this.readStatus = book.getReadStatus();
        this.readStart = book.getReadStart();
        this.readEnd = book.getReadEnd();
        this.summaryLink = book.getSummaryLink();
        this.memo = book.getMemo();
    }
}
