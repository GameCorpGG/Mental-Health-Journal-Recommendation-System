package com.Rachit.mental_health.Controller;

import com.Rachit.mental_health.dto.JournalEntryDTO;
import com.Rachit.mental_health.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class JournalController {

    private final JournalService journalService;

    @PostMapping("/create/{UserId}")
    public ResponseEntity<JournalEntryDTO> addJournalEntry(
            @PathVariable Long UserId,
            @RequestBody JournalEntryDTO entryDTO) {
        JournalEntryDTO createdEntry = journalService.addJournalEntry(UserId, entryDTO);
        return ResponseEntity.ok(createdEntry);
    }

    @GetMapping("/user/{UserId}")
    public ResponseEntity<List<JournalEntryDTO>> getEntriesByUserID(@PathVariable Long UserId) {
        List<JournalEntryDTO> entries = journalService.getEntriesByUserId(UserId);
        return ResponseEntity.ok(entries);
    }
}

