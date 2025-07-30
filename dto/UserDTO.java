package com.Rachit.mental_health.dto;

import lombok.Data;
import java.util.List;
import java.time.LocalDate;

@Data
public class UserDTO {
    private Long UserId;
    private String name;
    private String email;
    private String role;
    //private LocalDate createDate;
    private List<JournalEntryDTO> journalEntries;
    private List<MoodLogDTO> moodLogs;
    private List<ContentDTO> recommendedContent;
}
