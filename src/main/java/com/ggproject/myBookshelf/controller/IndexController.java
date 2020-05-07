package com.ggproject.myBookshelf.controller;

import com.ggproject.myBookshelf.config.auth.LoginUser;
import com.ggproject.myBookshelf.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(@LoginUser SessionUser loginUser) {

        if (loginUser != null) {
            return "redirect:/api/v1/books/list";
        } else {
            return "index";
        }
    }
}
