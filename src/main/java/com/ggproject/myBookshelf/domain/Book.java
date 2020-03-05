package com.ggproject.myBookshelf.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Book extends BaseTimeEntity{

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

    private String memo;

    public static Book create(User user, String bookName, String isbn, String author, ReadStatus readStatus) {
        Book book = new Book();
        book.setUser(user);
        book.name = bookName;
        book.isbn = isbn;
        book.author = author;
        book.readStatus = readStatus;

        return book;
    }

    public void update(ReadStatus readStatus, LocalDateTime readStart, LocalDateTime readEnd,
                       String summaryLink, String memo) {
        this.readStatus = readStatus;
        this.readStart = readStart;
        this.readEnd = readEnd;
        this.summaryLink = summaryLink;
        this.memo = memo;
    }

    private void setUser(User user) {
        this.user = user;
        user.getBookList().add(this);
    }
}
