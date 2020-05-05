package com.ggproject.myBookshelf.controller;

import com.ggproject.myBookshelf.config.auth.LoginUser;
import com.ggproject.myBookshelf.config.auth.dto.SessionUser;
import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import com.ggproject.myBookshelf.dto.BookListResponseDto;
import com.ggproject.myBookshelf.dto.BookSearchResponseDto;
import com.ggproject.myBookshelf.dto.BookUpdateRequestDto;
import com.ggproject.myBookshelf.service.BookService;
import com.ggproject.myBookshelf.service.api.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookSearchService bookSearchService;

    @GetMapping("/api/v1/books/readingList")
    public String booksReading(Model model, @LoginUser SessionUser user) {

        List<BookListResponseDto> bookList = bookService.findBooks(user.getId(), ReadStatus.READING);

        model.addAttribute("books", bookList);
        model.addAttribute("userName", user.getName());

        return "books/book-list-reading";
    }

    @GetMapping("/api/v1/books/plannedList")
    public String booksPlanned(Model model, @LoginUser SessionUser user) {

        List<BookListResponseDto> bookList = bookService.findBooks(user.getId(), ReadStatus.PLANNED);

        model.addAttribute("books", bookList);
        model.addAttribute("userName", user.getName());

        return "books/book-list-planned";
    }

    @GetMapping("/api/v1/books/completedList")
    public String booksCompleted(Model model, @LoginUser SessionUser user) {

        List<BookListResponseDto> bookList = bookService.findBooks(user.getId(), ReadStatus.COMPLETED);

        model.addAttribute("books", bookList);
        model.addAttribute("userName", user.getName());

        return "books/book-list-completed";
    }

    @GetMapping("/api/v1/books/new")
    public String createForm(Model model, @LoginUser SessionUser user) {

        model.addAttribute("userName", user.getName());
        model.addAttribute("searchKeyword", "");
        model.addAttribute("searchResult", new BookSearchResponseDto().getDocuments());

        return "books/book-search-save";
    }

    @GetMapping("/api/v1/books/new/{searchKeyword}")
    public String createForm(@PathVariable("searchKeyword") String searchKeyword,
                             Model model,
                             @LoginUser SessionUser user) {

        if (searchKeyword == "") {
            return  "redirect:/api/v1/books/new";
        }

        BookSearchResponseDto bookInformations = bookSearchService.getBookInformations(searchKeyword);

        model.addAttribute("userName", user.getName());
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("searchResult", bookInformations.getDocuments());

        return "books/book-search-save";
    }

    @GetMapping("/api/v1/books/{bookId}/update")
    public String updateForm(@PathVariable("bookId") Long bookId, Model model, @LoginUser SessionUser user) {

        Book findBook = bookService.findOne(bookId);
        BookUpdateRequestDto updateDto = new BookUpdateRequestDto(findBook);

        model.addAttribute("userName", user.getName());
        model.addAttribute("updateForm", updateDto);

        return "books/book-update";
    }
}
