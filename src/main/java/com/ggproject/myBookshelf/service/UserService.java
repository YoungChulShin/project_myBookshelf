package com.ggproject.myBookshelf.service;

import com.ggproject.myBookshelf.domain.User;
import com.ggproject.myBookshelf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public User findOneByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
