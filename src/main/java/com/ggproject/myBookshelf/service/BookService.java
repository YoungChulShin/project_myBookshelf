package com.ggproject.myBookshelf.service;

import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import com.ggproject.myBookshelf.domain.User;
import com.ggproject.myBookshelf.dto.UpdateBookDto;
import com.ggproject.myBookshelf.repository.BookRepository;
import com.ggproject.myBookshelf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(String userEmail, String bookName, String isbn, String author) {

        User user = userRepository.findOne(userEmail);
        Book book = Book.createBook(user, bookName, isbn, author);
        bookRepository.save(book);

        return book.getId();
    }

    @Transactional
    public void update(Long id, UpdateBookDto updateBookDto) {

        Book book = bookRepository.findOne(id);
        book.updateBook(updateBookDto);
    }

    @Transactional
    public void delete(Long id) {

        Book book = bookRepository.findOne(id);
        bookRepository.delete(book);
    }

    public List<Book> findBooks(ReadStatus readStatus) {
        return bookRepository.findByReadStatus(readStatus);
    }
}
