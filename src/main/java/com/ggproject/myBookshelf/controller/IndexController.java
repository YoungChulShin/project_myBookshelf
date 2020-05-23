package com.ggproject.myBookshelf.controller;

import com.ggproject.myBookshelf.config.auth.LoginUser;
import com.ggproject.myBookshelf.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(@LoginUser SessionUser loginUser) {

        if (loginUser != null) {
            return "redirect:/api/v1/books/plannedList";
        } else {
            return "index";
        }
    }
}
