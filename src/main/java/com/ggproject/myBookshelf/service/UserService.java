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

    public String join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getEmail();
    }

    private void validateDuplicateUser(User user) {
        User findUser = userRepository.findOne(user.getEmail());

        if (findUser == null) {
            throw new IllegalStateException("이미 존재하는 사용자입니다");
        }
    }

    public User findUser(String email) {
        return userRepository.findOne(email);
    }
}
