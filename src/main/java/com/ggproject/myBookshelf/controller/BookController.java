package com.ggproject.myBookshelf.controller;

import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import com.ggproject.myBookshelf.domain.User;
import com.ggproject.myBookshelf.dto.BookListResponseDto;
import com.ggproject.myBookshelf.dto.BookSaveRequestDto;
import com.ggproject.myBookshelf.dto.UserSaveRequestDto;
import com.ggproject.myBookshelf.service.BookService;
import com.ggproject.myBookshelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @GetMapping("/books/readingList")
    public String booksReading(Model model) {
        Long userId = 1L;
        List<BookListResponseDto> bookList = bookService.findBooks(userId, ReadStatus.READING);
        User user = userService.findOne(userId);

        model.addAttribute("books", bookList);
        model.addAttribute("userName", user.getName());

        return "books/book-list-reading";
    }

    @GetMapping("/books/plannedList")
    public String booksPlanned(Model model) {
        Long userId = 1L;
        List<BookListResponseDto> bookList = bookService.findBooks(userId, ReadStatus.PLANNED);
        User user = userService.findOne(userId);

        model.addAttribute("books", bookList);
        model.addAttribute("userName", user.getName());

        return "books/book-list-planned";
    }

    @GetMapping("/books/completedList")
    public String booksCompleted(Model model) {
        Long userId = 1L;
        List<BookListResponseDto> bookList = bookService.findBooks(userId, ReadStatus.COMPLETED);
        User user = userService.findOne(userId);

        model.addAttribute("books", bookList);
        model.addAttribute("userName", user.getName());

        return "books/book-list-completed";
    }

    @GetMapping("/books/new")
    public String createForm(Model model) {
        Long userId = 1L;
        User user = userService.findOne(userId);

        model.addAttribute("userName", user.getName());

        return "books/book-save";
    }

    @PostConstruct
    public void setup() {
        UserSaveRequestDto userSaveRequestDto = UserSaveRequestDto.builder()
                .email("go1323@gmail.com")
                .name("신영철")
                .build();

        Long userId = userService.save(userSaveRequestDto);

        BookSaveRequestDto bookSaveRequestDto = BookSaveRequestDto.builder()
                .bookName("JPA 북")
                .isbn("1234567890")
                .author("신영철")
                .readStatus(ReadStatus.COMPLETED)
                .build();

        bookService.save(userId, bookSaveRequestDto);
    }
}
