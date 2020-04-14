package com.ggproject.myBookshelf.repository;

import com.ggproject.myBookshelf.domain.Book;
import com.ggproject.myBookshelf.domain.ReadStatus;
import com.ggproject.myBookshelf.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {



//
//    public void save(Book book) {
//        em.persist(book);
//    }

//    public Book findOne(Long id) {
//        return em.find(Book.class, id);
//    }

    List<Book> findByUserAndReadStatus(User user, ReadStatus readStatus);
//
//    public List<Book> findByUser(User user, ReadStatus readStatus) {
//        return  em.createQuery("select b from Book b where b.user = :user and b.readStatus = :readStatus", Book.class)
//                .setParameter("user", user)
//                .setParameter("readStatus", readStatus)
//                .getResultList();
//    }
//
//    public void delete(Book book) {
//        em.remove(book);
//    }
}
