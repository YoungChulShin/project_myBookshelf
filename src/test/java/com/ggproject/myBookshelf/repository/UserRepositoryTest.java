package com.ggproject.myBookshelf.repository;

import com.ggproject.myBookshelf.domain.Role;
import com.ggproject.myBookshelf.domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void User_저장하기() {

        // given

        String userName = "신영철";
        String userEmail = "test@gmail.com";

        User user = User.builder().name("신영철").email(userEmail).role(Role.USER).build();
        userRepository.save(user);

        // when
        List<User> userList = userRepository.findAll();

        // then
        User findUser = userList.get(0);
        assertThat(findUser.getName()).isEqualTo(userName);
        assertThat(findUser.getEmail()).isEqualTo(userEmail);
    }
}
