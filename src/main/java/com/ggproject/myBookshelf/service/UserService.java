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
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("사용자 정보를 찾을 수 없습니다. Id: " + id)));
    }

    public User findOneByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
