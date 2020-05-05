package com.ggproject.myBookshelf.controller;

import com.ggproject.myBookshelf.config.auth.dto.SessionUser;
import com.ggproject.myBookshelf.dto.BookSaveRequestDto;
import com.ggproject.myBookshelf.dto.BookUpdateRequestDto;
import com.ggproject.myBookshelf.service.BookService;
import com.ggproject.myBookshelf.service.api.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;
    private final HttpSession httpSession;
    private final BookSearchService bookSearchService;

    @PostMapping("/api/v1/books/new")
    public Long create(@RequestBody BookSaveRequestDto bookSaveRequest, Model model) {

        SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");
        return bookService.save(sessionUser.getId(), bookSaveRequest);
    }

    @PostMapping("/api/v1/books/{bookId}/update")
    public Long update(@PathVariable("bookId") Long bookId, @RequestBody BookUpdateRequestDto bookUpdateRequest) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if (bookUpdateRequest.getReadStartString() != "") {
            bookUpdateRequest.setReadStart(LocalDateTime.parse(bookUpdateRequest.getReadStartString(), formatter));
        }
        if (bookUpdateRequest.getReadEndString() != "") {
            bookUpdateRequest.setReadEnd(LocalDateTime.parse(bookUpdateRequest.getReadEndString(), formatter));
        }

        return bookService.update(bookId, bookUpdateRequest);
    }

    @PostMapping("/api/v1/books/{bookId}/delete")
    public long delete(@PathVariable("bookId") Long bookId) {

        bookService.delete(bookId);
        return bookId;
    }
}
