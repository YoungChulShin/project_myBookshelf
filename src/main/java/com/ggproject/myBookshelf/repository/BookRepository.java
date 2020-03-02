package com.ggproject.myBookshelf.repository;

import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookRepository {

    private final EntityManager em;

    public void save(Book book) {
        em.persist(book);
    }

    public Book findOne(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findByReadStatus(ReadStatus readStatus) {
        return  em.createQuery("select b from Book b where b.readStatus = :readStatus", Book.class)
                .setParameter("readStatus", readStatus.toString())
                .getResultList();
    }

    public void delete(Book book) {
        em.remove(book);
    }
}
