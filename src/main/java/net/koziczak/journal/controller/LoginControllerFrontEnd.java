package net.koziczak.journal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControllerFrontEnd {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
