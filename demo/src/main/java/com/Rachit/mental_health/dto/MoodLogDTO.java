package com.Rachit.mental_health.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MoodLogDTO {
    private Long logId;
    private Long UserId;
    private String moodType;
    private LocalDate date;
    private String note;
}