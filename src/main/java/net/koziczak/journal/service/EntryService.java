package net.koziczak.journal.service;

import net.koziczak.journal.configuration.WebSecurityConfiguration;
import net.koziczak.journal.model.Entry;
import net.koziczak.journal.model.User;
import net.koziczak.journal.model.dto.EntryDto;
import net.koziczak.journal.model.dto.UserDto;
import net.koziczak.journal.repository.EntryRepository;
import net.koziczak.journal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EntryService {

    private EntryRepository entryRepository;
    private LoginService loginService;
    private UserRepository userRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    // stworz nowe entry
    public Entry createEntry (EntryDto entryDto){
        Entry entry = new Entry(entryDto.getTitle(), entryDto.getEntry_text(), userRepository.getOne(1L));
        return entryRepository.save(entry);
    }

    public List<Entry> getAllEntries(){
        return entryRepository.findAll();
    }

}
