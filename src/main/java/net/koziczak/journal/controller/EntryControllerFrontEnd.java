package net.koziczak.journal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntryControllerFrontEnd {
    @GetMapping("/entry")
    public String login(){
        return "entry";
    }

}
