package net.koziczak.journal.controller;

import net.koziczak.journal.model.Entry;
import net.koziczak.journal.model.User;
import net.koziczak.journal.model.dto.EntryDto;
import net.koziczak.journal.service.EntryService;
import net.koziczak.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/")
@Controller
public class EntryController {
    private EntryService entryService;
    private UserService userService;

    @Autowired
    public EntryController(EntryService entryService, UserService userService) {
        this.entryService = entryService;
        this.userService = userService;

    }

    @PostMapping("/addEntry")
    public String createNewEntry(@ModelAttribute EntryDto entryDto, @ModelAttribute User user)
    {
        entryService.createEntry(entryDto, user.getEmail());
        return "redirect:/entry";
    }


    //
    @GetMapping("/entries")
    public List<Entry> getAllEntries(){
        return entryService.getAllEntries();
    }



    //
    @GetMapping("/entry")
    public String entry(Model model){
        model.addAttribute("entryDto", new EntryDto());
        model.addAttribute("entries", entryService.getAllEntries());

        return "entry";
    }



}