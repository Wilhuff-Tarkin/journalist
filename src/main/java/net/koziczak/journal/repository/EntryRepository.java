package net.koziczak.journal.repository;

import net.koziczak.journal.model.Entry;
import net.koziczak.journal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
