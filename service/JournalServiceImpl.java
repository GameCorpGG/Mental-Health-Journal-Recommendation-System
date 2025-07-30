package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.JournalEntryDTO;
import com.Rachit.mental_health.entity.JournalEntry;
import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.repository.JournalRepository;
import com.Rachit.mental_health.repository.UserRepository;
import com.Rachit.mental_health.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public JournalEntryDTO addJournalEntry(String username, JournalEntryDTO dto) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with the username: " + username));

        JournalEntry entry = new JournalEntry();
        entry.setUser(user);
        entry.setTitle(dto.getTitle());
        entry.setContent(dto.getContent());
        entry.setEntryDate(LocalDateTime.now());

        JournalEntry saved = journalRepository.save(entry);

        JournalEntryDTO response = new JournalEntryDTO();
        response.setEntryId(saved.getJournalId());
        response.setUserId(saved.getUser().getUserId());
        response.setTitle(saved.getTitle());
        response.setContent(saved.getContent());
        response.setEntryDate(saved.getEntryDate());

        return response;
    }

    @Override
    public List<JournalEntryDTO> getEntriesByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return journalRepository.findByUser(user).stream().map(entry -> {
            JournalEntryDTO dto = new JournalEntryDTO();
            dto.setEntryId(entry.getJournalId());
            dto.setTitle(entry.getTitle());
            dto.setEntryDate(entry.getEntryDate());
            dto.setContent(entry.getContent());
            return dto;
        }).collect(Collectors.toList());
    }
}
