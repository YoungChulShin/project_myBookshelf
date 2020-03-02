package com.ggproject.myBookshelf.repository;

import com.ggproject.myBookshelf.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }

    public User findOne(String email) {
        return entityManager.find(User.class, email);
    }
}
