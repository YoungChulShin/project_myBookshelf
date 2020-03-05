package com.ggproject.myBookshelf.service;

import com.ggproject.myBookshelf.domain.User;
import com.ggproject.myBookshelf.dto.UserSaveRequestDto;
import com.ggproject.myBookshelf.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Rollback(value = false)
    @Test
    public void 사용자_추가() throws Exception {

        // given
        UserSaveRequestDto user = createUserDto("ycshin", "go1323@gmail.com");

        // when
        Long userId = userService.save(user);

        // then
        Assert.assertEquals(user, userRepository.findOne(userId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_사용자_추가() throws Exception {
        // given
        UserSaveRequestDto user1 = createUserDto("ycshin", "go1323@gmail.com");

        UserSaveRequestDto user2 = createUserDto("mjseo", "go1323@gmail.com");

        // when
        userService.save(user1);
        userService.save(user2);

        // then
        Assert.fail();
    }

    private UserSaveRequestDto createUserDto(String name, String email) {
        return UserSaveRequestDto.builder()
                .name(name)
                .email(email)
                .build();
    }
}