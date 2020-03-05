package com.ggproject.myBookshelf.service;

import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import com.ggproject.myBookshelf.domain.User;
import com.ggproject.myBookshelf.dto.BookSaveRequestDto;
import com.ggproject.myBookshelf.dto.BookUpdateRequestDto;
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
import java.util.List;


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
        BookSaveRequestDto requestDto = createBookSaveRequestDto();

        // when
        Long bookId = bookService.save(user.getId(), requestDto);

        // then
        Book findBook = bookService.findOne(bookId);

        Assert.assertEquals(ReadStatus.PLANNED, findBook.getReadStatus());
        Assert.assertEquals("JPA 북", findBook.getName());
        Assert.assertEquals("1234567890", findBook.getIsbn());
        Assert.assertEquals(user.getBookList().get(0), findBook);
    }

    @Test
    public void 책_삭제() {
        // given
        User user = createUser();
        BookSaveRequestDto requestDto = createBookSaveRequestDto();

        Long bookId = bookService.save(user.getId(), requestDto);

        // when
        bookService.delete(bookId);

        // then
        Assert.assertEquals(null, bookRepository.findOne(bookId));
    }

    @Test
    public void 책_수정() {
        // given
        User user = createUser();
        BookSaveRequestDto requestDto = createBookSaveRequestDto();

        Long bookId = bookService.save(user.getId(), requestDto);

        BookUpdateRequestDto bookUpdateRequestDto =  BookUpdateRequestDto.builder()
                .readStatus(ReadStatus.READING)
                .memo("testMemo")
                .summaryLink("www.google.com")
                .readStart(LocalDateTime.now())
                .build();

        // when
        bookService.update(bookId, bookUpdateRequestDto);

        // then
        Book findBook = bookRepository.findOne(bookId);

        Assert.assertEquals(bookUpdateRequestDto.getReadStatus(), findBook.getReadStatus());
        Assert.assertEquals(bookUpdateRequestDto.getMemo(), findBook.getMemo());
        Assert.assertEquals(bookUpdateRequestDto.getSummaryLink(), findBook.getSummaryLink());
        Assert.assertEquals(bookUpdateRequestDto.getReadStart(), findBook.getReadStart());
    }

    @Test
    public void 사용자_책_조회() {

        // given
        User user = createUser();
        BookSaveRequestDto requestDto = createBookSaveRequestDto();
        Long bookId = bookService.save(user.getId(), requestDto);

        // when
        List<Book> findBooks = bookService.findBooks(user.getId(), ReadStatus.PLANNED);

        // then
        Assert.assertEquals(1, findBooks.size());
        Assert.assertEquals(ReadStatus.PLANNED, findBooks.get(0).getReadStatus());
        Assert.assertEquals("JPA 북", findBooks.get(0).getName());
    }

    private User createUser() {
        User user = User.create("go1323@gmail.com", "신영철");
        em.persist(user);

        return user;
    }

    private BookSaveRequestDto createBookSaveRequestDto(){
        return  BookSaveRequestDto.builder()
                .bookName("JPA 북")
                .isbn("1234567890")
                .author("신영철")
                .readStatus(ReadStatus.PLANNED)
                .build();
    }
}