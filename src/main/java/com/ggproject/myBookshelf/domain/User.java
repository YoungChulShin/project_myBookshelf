package com.ggproject.myBookshelf.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column
    private String picture;

    @OneToMany(mappedBy = "user")
    private List<Book> bookList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

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
