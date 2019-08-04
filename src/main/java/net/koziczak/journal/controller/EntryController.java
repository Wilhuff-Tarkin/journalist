package net.koziczak.journal.controller;

import net.koziczak.journal.model.Entry;
import net.koziczak.journal.model.dto.EntryDto;
import net.koziczak.journal.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/")
@RestController
public class EntryController {
    private EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping("/entry/{title}&{entry_text}")
    public Entry createNewEntry(
            @PathVariable String title, @PathVariable String entry_text
    ){
        return entryService.createEntry(new EntryDto(title, entry_text));
    }

//    @GetMapping("/Entries")
//    public List<Entry> getAllEntries(){
//        return entryService.getAllEntries();
//    }


}