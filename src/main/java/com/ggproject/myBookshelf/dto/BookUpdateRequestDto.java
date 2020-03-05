package com.ggproject.myBookshelf.dto;

import com.ggproject.myBookshelf.domain.ReadStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BookUpdateRequestDto {

    private ReadStatus readStatus;
    private LocalDateTime readStart;
    private LocalDateTime readEnd;
    private String summaryLink;
    private String memo;

    @Builder
    public BookUpdateRequestDto(ReadStatus readStatus,
                                LocalDateTime readStart,
                                LocalDateTime readEnd,
                                String summaryLink,
                                String memo) {
        this.readStatus = readStatus;
        this.readStart = readStart;
        this.readEnd = readEnd;
        this.summaryLink = summaryLink;
        this.memo = memo;
    }
}
