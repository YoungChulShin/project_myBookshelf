package com.ggproject.myBookshelf.service;

import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import com.ggproject.myBookshelf.domain.User;
import com.ggproject.myBookshelf.dto.BookListResponseDto;
import com.ggproject.myBookshelf.dto.BookSaveRequestDto;
import com.ggproject.myBookshelf.dto.BookUpdateRequestDto;
import com.ggproject.myBookshelf.repository.BookRepository;
import com.ggproject.myBookshelf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(Long userId, BookSaveRequestDto requestDto) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new IllegalArgumentException("사용자 정보가 없습니다. Id:" + userId);
        }

        Book book = Book.create(user,
                requestDto.getBookName(),
                requestDto.getIsbn(),
                requestDto.getAuthor());
        bookRepository.save(book);

        return book.getId();
    }

    @Transactional
    public Long update(Long id, BookUpdateRequestDto requestDto) {
        Book book = bookRepository.findOne(id);

        if (book == null) {
            throw new IllegalArgumentException("해당 책 정보가 없습니다. Id: " + id);
        }

        book.update(requestDto.getReadStatus(),
                requestDto.getReadStart(),
                requestDto.getReadEnd(),
                requestDto.getSummaryLink(),
                requestDto.getMemo());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Book book = bookRepository.findOne(id);
        bookRepository.delete(book);
    }

    public Book findOne(Long id) {
        return bookRepository.findOne(id);
    }

    public List<BookListResponseDto> findBooks(Long userId, ReadStatus readStatus) {
        User user = userRepository.findOne(userId);

        return bookRepository.findByUser(user, readStatus).stream()
                .map(BookListResponseDto::new)
                .collect(Collectors.toList());
    }
}
