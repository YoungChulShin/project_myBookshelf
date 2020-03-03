package com.ggproject.myBookshelf.service;

import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import com.ggproject.myBookshelf.domain.User;
import com.ggproject.myBookshelf.dto.UpdateBookDto;
import com.ggproject.myBookshelf.repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookServiceTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Autowired
    EntityManager em;

    @Test
    public void 책_추가() {
        // given
        User user = createUser();

        // when
        Long bookId = bookService.save(user.getId(), "JPA", "1234567890", "신영철");

        // then
        Book findBook = bookService.findOne(bookId);

        Assert.assertEquals(ReadStatus.PLANNED, findBook.getReadStatus());
        Assert.assertEquals("JPA", findBook.getName());
        Assert.assertEquals("1234567890", findBook.getIsbn());
        Assert.assertEquals(user.getBookList().get(0), findBook);
    }

    @Test
    public void 책_삭제() {
        // given
        User user = createUser();
        Long bookId = bookService.save(user.getId(), "JPA", "1234567890", "신영철");

        // when
        bookService.delete(bookId);

        // then
        Assert.assertEquals(null, bookRepository.findOne(bookId));
    }

    @Test
    public void 책_수정() {
        // given
        User user = createUser();
        Long bookId = bookService.save(user.getId(), "JPA", "1234567890", "신영철");

        UpdateBookDto updateBookDto = new UpdateBookDto();
        updateBookDto.setReadStatus(ReadStatus.READING);
        updateBookDto.setMemo("testMemo");
        updateBookDto.setSummaryLink("www.google.com");
        updateBookDto.setReadStart(LocalDateTime.now());

        // when
        bookService.update(bookId, updateBookDto);

        // then
        Book findBook = bookRepository.findOne(bookId);

        Assert.assertEquals(updateBookDto.getReadStatus(), findBook.getReadStatus());
        Assert.assertEquals(updateBookDto.getMemo(), findBook.getMeno());
        Assert.assertEquals(updateBookDto.getSummaryLink(), findBook.getSummaryLink());
        Assert.assertEquals(updateBookDto.getReadStart(), findBook.getReadStart());
    }

    private User createUser() {
        User user = new User();
        user.update("go1323@gmail.com", "ycshin");
        em.persist(user);

        return user;
    }
}