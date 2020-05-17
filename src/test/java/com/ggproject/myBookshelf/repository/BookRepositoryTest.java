package com.ggproject.myBookshelf.repository;

import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import com.ggproject.myBookshelf.domain.Role;
import com.ggproject.myBookshelf.domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @After
    public void cleanup() {
        bookRepository.deleteAll();
    }

    @Test
    public void Book_저장하기() {

        // given
        String author = "영철신";
        String title = "스프링공부하자";
        String email = "test@gmail.com";

        User user = User.builder().name("신영철").email(email).role(Role.USER).build();
        userRepository.save(user);

        Book book = Book.create(user, title, "1234567890", author, "");
        bookRepository.save(book);

        // when
        List<Book> bookList = bookRepository.findAll();

        // then
        Book findBook = bookList.get(0);
        assertThat(findBook.getAuthor()).isEqualTo(author);
        assertThat(findBook.getName()).isEqualTo(title);
    }

    @Test
    public void Book_저장하고_사용자와_읽기상태로_불러오기() {

        // given
        String author = "영철신";
        String title = "스프링공부하자";
        String email = "test@gmail.com";

        User user = User.builder().name("신영철").email(email).role(Role.USER).build();
        userRepository.save(user);

        Book book = Book.create(user, title, "1234567890", author, "");
        bookRepository.save(book);

        // when
        List<Book> bookList = bookRepository.findByUserAndReadStatus(user, ReadStatus.PLANNED);

        // then
        Book findBook = bookList.get(0);
        assertThat(findBook.getAuthor()).isEqualTo(author);
        assertThat(findBook.getName()).isEqualTo(title);
    }
}