package com.ggproject.myBookshelf.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTest {

    @Test
    public void Book_생성() {

        // given
        String author = "영철신";
        String title = "스프링공부하자";
        String email = "test@gmail.com";
        String infoLink = "www.google.com";
        String isbn = "1234567890";
        ReadStatus readStatus = ReadStatus.PLANNED;
        User user = User.builder().name("신영철").email(email).role(Role.USER).build();

        // when
        Book createdBook = Book.create(user, title, isbn, author, infoLink);

        // then
        assertThat(createdBook.getName()).isEqualTo(title);
        assertThat(createdBook.getIsbn()).isEqualTo(isbn);
        assertThat(createdBook.getAuthor()).isEqualTo(author);
        assertThat(createdBook.getInfoLink()).isEqualTo(infoLink);
        assertThat(createdBook.getReadStatus()).isEqualTo(readStatus);
        assertThat(createdBook.getUser().getName()).isEqualTo(user.getName());
    }

    @Test
    public void Book_수정() {
        // given
        String author = "영철신";
        String title = "스프링공부하자";
        String email = "test@gmail.com";
        String infoLink = "www.google.com";
        String isbn = "1234567890";
        ReadStatus readStatus = ReadStatus.PLANNED;
        User user = User.builder().name("신영철").email(email).role(Role.USER).build();
        Book updateBook = Book.create(user, title, isbn, author, infoLink);

        ReadStatus updateReadStatus = ReadStatus.READING;
        LocalDateTime updateReadStart = LocalDateTime.of(LocalDate.of(2020,01,1), LocalTime.of(12,12));
        LocalDateTime updateReadEnd = null;
        String updateSummaryLink = "www.naver.com";
        String updateMemo = "업데이트 테스트";

        // when
        updateBook.update(updateReadStatus, updateReadStart, updateReadEnd, updateSummaryLink, updateMemo);

        // then
        assertThat(updateBook.getReadStatus()).isEqualTo(updateReadStatus);
        assertThat(updateBook.getReadStart()).isEqualTo(updateReadStart);
        assertThat(updateBook.getReadEnd()).isEqualTo(updateReadEnd);
        assertThat(updateBook.getSummaryLink()).isEqualTo(updateSummaryLink);
        assertThat(updateBook.getMemo()).isEqualTo(updateMemo);
    }
}
