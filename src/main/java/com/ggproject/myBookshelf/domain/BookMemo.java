package com.ggproject.myBookshelf.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class BookMemo {

    @Id
    @GeneratedValue
    @Column(name = "bookMemo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime time;

    private String memo;
}
