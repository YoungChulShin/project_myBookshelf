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

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @GetMapping("/books")
    public String bookList(Model model) {
        Long userId = new Long(1);
        List<BookListResponseDto> bookList = bookService.findBooks(userId, ReadStatus.READING);
        User user = userService.findOne(userId);

        model.addAttribute("books", bookList);
        model.addAttribute("userName", user.getName());

        return "books/bookListPlanned";
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
                .readStatus(ReadStatus.READING)
                .build();

        bookService.save(userId, bookSaveRequestDto);
    }
}
