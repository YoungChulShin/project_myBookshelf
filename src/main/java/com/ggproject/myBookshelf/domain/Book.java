package com.ggproject.myBookshelf.domain;

import lombok.Getter;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User user;

    private String name;

    private String isbn;

    private String author;

    @Enumerated(EnumType.STRING)
    private ReadStatus readStatus = ReadStatus.PLANNED;

    private LocalDateTime readStart;

    private LocalDateTime readEnd;

    private String summaryLink;

    @OneToMany(mappedBy = "book")
    private List<BookMemo> bookMemoList = new ArrayList<>();
}
