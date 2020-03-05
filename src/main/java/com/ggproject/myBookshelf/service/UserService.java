package com.ggproject.myBookshelf.service;

import com.ggproject.myBookshelf.domain.User;
import com.ggproject.myBookshelf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByEmail(user.getEmail());

        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 사용자입니다");
        }
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }
}
