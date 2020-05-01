package com.ggproject.myBookshelf.controller;

import com.ggproject.myBookshelf.config.auth.LoginUser;
import com.ggproject.myBookshelf.config.auth.dto.SessionUser;
import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import com.ggproject.myBookshelf.dto.BookListResponseDto;
import com.ggproject.myBookshelf.dto.BookSaveRequestDto;
import com.ggproject.myBookshelf.dto.BookSearchResponseDto;
import com.ggproject.myBookshelf.dto.BookUpdateRequestDto;
import com.ggproject.myBookshelf.service.BookService;
import com.ggproject.myBookshelf.service.api.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final HttpSession httpSession;
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

        //return "books/book-search-save";
        return "books/book-list-planned";
    }

    @PostMapping("/api/v1/books/new")
    public String create(@Valid BookSaveRequestDto form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("saveForm", form);
            return "books/book-search-save";
        }

        SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");
        bookService.save(sessionUser.getId(), form);

        return "redirect:/";
    }

    @GetMapping("/api/v1/books/{bookId}/update")
    public String updateForm(@PathVariable("bookId") Long bookId, Model model) {

        Book findBook = bookService.findOne(bookId);
        BookUpdateRequestDto updateDto = new BookUpdateRequestDto(findBook);

        model.addAttribute("updateForm", updateDto);

        return "books/book-update";
    }

    @PostMapping("/api/v1/books/{bookId}/update")
    public String update(@PathVariable("bookId") Long bookId, BookUpdateRequestDto form) {

        form.setId(bookId);
        if (form.getReadStartString() == "") {
            form.setReadStart(null);
        } else {
            form.setReadStart(LocalDate.parse(form.getReadStartString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        if (form.getReadEndString() == "") {
            form.setReadEnd(null);
        } else {
            form.setReadEnd(LocalDate.parse(form.getReadEndString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        bookService.update(bookId, form);

        return getBookListPageAddress(form.getReadStatus());
    }

    @PostMapping("/api/v1/books/{bookId}/delete")
    public String delete(@PathVariable("bookId") Long bookId) {

        Book book = bookService.findOne(bookId);
        ReadStatus readStatus = book.getReadStatus();
        bookService.delete(bookId);

        return getBookListPageAddress(readStatus);
    }

    private String getBookListPageAddress(ReadStatus readStatus) {

        if (readStatus == ReadStatus.PLANNED) {
            return "redirect:/api/v1/books/plannedList";
        } else if (readStatus == ReadStatus.READING) {
            return "redirect:/api/v1/books/readingList";
        } else {
            return "redirect:/api/v1/books/completedList";
        }
    }
}
