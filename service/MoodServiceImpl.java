package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.MoodLogDTO;
import com.Rachit.mental_health.entity.MoodLog;
import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.exception.ResourceNotFoundException;
import com.Rachit.mental_health.repository.MoodLogRepository;
import com.Rachit.mental_health.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoodServiceImpl implements MoodService {

    private final MoodLogRepository moodLogRepository;
    private final UserRepository userRepository;

    @Override
    public MoodLogDTO logMood(String username, MoodLogDTO dto) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        MoodLog log = new MoodLog();
        log.setUser(user);
        log.setMood(dto.getMoodType());
        log.setDate(LocalDateTime.now());
        log.setNote(dto.getNote()); // assuming note is added

        MoodLog saved = moodLogRepository.save(log);

        MoodLogDTO response = new MoodLogDTO();
        response.setLogId(saved.getMoodLogId());
        response.setUserId(user.getUserId());
        response.setMoodType(saved.getMood());
        response.setDate(saved.getDate());
        response.setNote(saved.getNote());

        return response;
    }

    @Override
    public List<MoodLogDTO> getMoodLogsByUser(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return moodLogRepository.findByUser(user).stream().map(log -> {
            MoodLogDTO dto = new MoodLogDTO();
            dto.setLogId(log.getMoodLogId());
            dto.setUserId(user.getUserId());
            dto.setMoodType(log.getMood());
            dto.setDate(log.getDate());
            dto.setNote(log.getNote());
            return dto;
        }).collect(Collectors.toList());
    }
}
