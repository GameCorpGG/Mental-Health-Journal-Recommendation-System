package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.JournalEntryDTO;
import java.util.List;

public interface JournalService {
    JournalEntryDTO addJournalEntry(Long UserId, JournalEntryDTO entryDTO);
    List<JournalEntryDTO> getEntriesByUserId(Long UserId);
}
