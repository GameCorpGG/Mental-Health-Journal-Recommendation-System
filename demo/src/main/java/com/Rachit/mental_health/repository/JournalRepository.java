package com.Rachit.mental_health.repository;

import com.Rachit.mental_health.entity.JournalEntry;
import com.Rachit.mental_health.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalRepository extends JpaRepository<JournalEntry, Long> {
    List<JournalEntry> findByUser(User user);
}
