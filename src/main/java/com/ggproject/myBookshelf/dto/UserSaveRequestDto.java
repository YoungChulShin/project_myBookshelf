package com.ggproject.myBookshelf.dto;

import com.ggproject.myBookshelf.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveRequestDto {

    private String email;
    private String name;

    @Builder
    public UserSaveRequestDto(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public User toEntity() {
        return User.create(email, name);
    }
}
