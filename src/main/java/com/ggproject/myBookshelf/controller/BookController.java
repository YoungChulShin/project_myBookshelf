package com.ggproject.myBookshelf.controller;

import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import com.ggproject.myBookshelf.domain.User;
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
        long userId = 1;
        List<Book> bookList = bookService.findBooks(userId, ReadStatus.READING);
        User user = userService.findOne(userId);

        model.addAttribute("books", bookList);
        model.addAttribute("userName", user.getName());

        return "books/bookList";
    }

    @PostConstruct
    public void setup() {
        User user = new User();
        user.update("go1323@gmail.com", "신영철");
        userService.join(user);

        Long bookId = bookService.save(user.getId(), "JPA 북", "1234567890", "신영철", ReadStatus.READING);

    }
}
