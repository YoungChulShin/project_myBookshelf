package com.ggproject.myBookshelf.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Test
    public void User_생성() {

        // given
        String name = "신영철";
        String email = "test@gmail.com";
        String pictrue = "http://testPicutre.com";
        Role role = Role.USER;

        // when
        User createdUser = User.builder().name(name).email(email).picture(pictrue).role(role).build();

        // then
        assertThat(createdUser.getName()).isEqualTo(name);
        assertThat(createdUser.getEmail()).isEqualTo(email);
        assertThat(createdUser.getPicture()).isEqualTo(pictrue);
        assertThat(createdUser.getRole()).isEqualTo(role);
    }

    @Test
    public void User_수정() {

        // given
        String name = "신영철";
        String email = "test@gmail.com";
        String pictrue = "http://testPicutre.com";
        Role role = Role.USER;

        User updatedUser = User.builder().name(name).email(email).picture(pictrue).role(role).build();

        String updateName = "새로운_신영철";
        String updatePicutre = "http://newTestPicutre.com";

        // when
        updatedUser.update(updateName, updatePicutre);

        // then
        assertThat(updatedUser.getName()).isEqualTo(updateName);
        assertThat(updatedUser.getEmail()).isEqualTo(email);
        assertThat(updatedUser.getPicture()).isEqualTo(updatePicutre);
        assertThat(updatedUser.getRole()).isEqualTo(role);

    }
}
