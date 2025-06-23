package com.Rachit.mental_health.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class JournalEntryDTO {
    private Long entryId;
    private Long UserId;
    private String title;
    private String content;
    private LocalDate entryDate;
}