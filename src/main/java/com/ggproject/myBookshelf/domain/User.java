package com.ggproject.myBookshelf.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Book> bookList = new ArrayList<>();

    public static User create(String email, String name) {
        User user = new User();
        user.email = email;
        user.name = name;

        return user;
    }

    public void update(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
