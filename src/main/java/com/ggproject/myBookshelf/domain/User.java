package com.ggproject.myBookshelf.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User {

    @Id
    private String email;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Book> bookList = new ArrayList<>();
}
