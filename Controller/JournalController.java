package com.Rachit.mental_health.Controller;

import com.Rachit.mental_health.dto.JournalEntryDTO;
import com.Rachit.mental_health.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class JournalController {

    private final JournalService journalService;

    @PostMapping("/create")
    public ResponseEntity<JournalEntryDTO> addJournalEntry(
            @RequestBody JournalEntryDTO entryDTO,
            Authentication authentication) {
        String username = authentication.getName();
        JournalEntryDTO createdEntry = journalService.addJournalEntry(username, entryDTO);
        return ResponseEntity.ok(createdEntry);
    }

    @GetMapping("/history")
    public ResponseEntity<List<JournalEntryDTO>> getEntriesByUserID(Authentication authentication) {
        String username = authentication.getName();
        List<JournalEntryDTO> entries = journalService.getEntriesByUsername(username);
        return ResponseEntity.ok(entries);
    }
}

