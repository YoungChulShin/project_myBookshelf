package com.ggproject.myBookshelf.domain;

public enum ReadStatus {
    PLANNED("읽을 예정"),
    READING("읽는 중"),
    COMPLETED("완료");

    private final String displayValue;

    private ReadStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}