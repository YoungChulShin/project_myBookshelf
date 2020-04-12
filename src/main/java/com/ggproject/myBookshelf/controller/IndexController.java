package com.ggproject.myBookshelf.controller;

import com.ggproject.myBookshelf.config.auth.dto.SessionUser;
import com.ggproject.myBookshelf.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index() {

        httpSession.removeAttribute("user");
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            return "redirect:/api/v1/books/plannedList";
        } else {
            return "index";
        }
    }
}
