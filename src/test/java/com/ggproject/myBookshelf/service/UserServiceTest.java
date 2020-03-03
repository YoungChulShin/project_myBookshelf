package com.ggproject.myBookshelf.service;

import com.ggproject.myBookshelf.domain.User;
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
        User user = new User();
        user.update("go1323@gmail.com", "ycshin");

        // when
        Long userId = userService.join(user);

        // then
        Assert.assertEquals(user, userRepository.findOne(userId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_사용자_추가() throws Exception {
        // given
        User user1 = new User();
        user1.update("go1323@gmail.com", "ycshin");

        User user2 = new User();
        user2.update("go1323@gmail.com", "mjseo");

        // when
        userService.join(user1);
        userService.join(user2);

        // then
        Assert.fail();
    }
}