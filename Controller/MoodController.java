package com.Rachit.mental_health.Controller;

import com.Rachit.mental_health.dto.MoodLogDTO;
import com.Rachit.mental_health.service.MoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mood")
@RequiredArgsConstructor
public class MoodController {

    private final MoodService moodService;

    @PostMapping("/log")
    public ResponseEntity<MoodLogDTO> addMoodLog(
            @RequestBody MoodLogDTO moodLogDTO,
            Authentication authentication) {
        String username = authentication.getName();
        MoodLogDTO savedLog = moodService.logMood(username, moodLogDTO);
        return ResponseEntity.ok(savedLog);
    }

    @GetMapping("/get")
    public ResponseEntity<List<MoodLogDTO>> getMoodLogsByUserId(Authentication authentication) {
        String username = authentication.getName();
        List<MoodLogDTO> logs = moodService.getMoodLogsByUser(username);
        return ResponseEntity.ok(logs);
    }
}

