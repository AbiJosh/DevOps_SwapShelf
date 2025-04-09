package com.swapshelf.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "index"; // templates/index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // templates/login.html
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // templates/register.html
    }
    @GetMapping("/chat")
    public String chat() {
        return "chat"; // maps to templates/chat.html
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile"; // maps to templates/profile.html
    }

    @GetMapping("/home")
    public String userHome() {
        return "home"; // maps to templates/home.html
    }
}
