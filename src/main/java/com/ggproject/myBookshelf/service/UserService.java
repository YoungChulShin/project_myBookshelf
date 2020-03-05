package com.ggproject.myBookshelf.service;

import com.ggproject.myBookshelf.domain.User;
import com.ggproject.myBookshelf.dto.UserSaveRequestDto;
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
    public Long save(UserSaveRequestDto userDto) {
        validateDuplicateUser(userDto.getEmail());

        User user = userDto.toEntity();
        userRepository.save(user);

        return user.getId();
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    private void validateDuplicateUser(String email) {
        List<User> findUsers = userRepository.findByEmail(email);

        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 사용자입니다");
        }
    }
}
