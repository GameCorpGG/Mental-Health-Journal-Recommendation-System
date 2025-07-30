package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.JournalEntryDTO;
import java.util.List;

public interface JournalService {
    JournalEntryDTO addJournalEntry(String username, JournalEntryDTO entryDTO);
    List<JournalEntryDTO> getEntriesByUsername(String username);
}
