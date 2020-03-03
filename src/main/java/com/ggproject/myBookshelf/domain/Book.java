package com.ggproject.myBookshelf.domain;

import com.ggproject.myBookshelf.dto.UpdateBookDto;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private String isbn;

    private String author;

    @Enumerated(EnumType.STRING)
    private ReadStatus readStatus;

    private LocalDateTime readStart;

    private LocalDateTime readEnd;

    private String summaryLink;

    private String meno;

    public static Book createBook(User user, String bookName, String isbn, String author) {
        Book book = new Book();
        book.setUser(user);
        book.name = bookName;
        book.isbn = isbn;
        book.author = author;
        book.readStatus = ReadStatus.PLANNED;

        return book;
    }

    public void updateBook(UpdateBookDto updateBookDto) {
        this.readStatus = updateBookDto.getReadStatus();
        this.readStart = updateBookDto.getReadStart();
        this.readEnd = updateBookDto.getReadEnd();
        this.summaryLink = updateBookDto.getSummaryLink();
        this.meno = updateBookDto.getMemo();
    }

    private void setUser(User user) {
        this.user = user;
        user.getBookList().add(this);
    }
}
