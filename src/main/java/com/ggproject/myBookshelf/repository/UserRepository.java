package com.ggproject.myBookshelf.repository;

import com.ggproject.myBookshelf.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findOne(String email) {
        return em.find(User.class, email);
    }
}
