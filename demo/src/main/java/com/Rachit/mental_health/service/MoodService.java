package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.MoodLogDTO;

import java.util.List;

public interface MoodService {
    MoodLogDTO logMood(Long UserId, MoodLogDTO moodLogDTO);
    List<MoodLogDTO> getMoodLogsByUser(Long UserId);
}
