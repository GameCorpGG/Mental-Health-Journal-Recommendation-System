package com.Rachit.mental_health.Controller;

import com.Rachit.mental_health.dto.MoodLogDTO;
import com.Rachit.mental_health.service.MoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mood")
@RequiredArgsConstructor
public class MoodController {

    private final MoodService moodService;

    @PostMapping("/log/{UserId}")
    public ResponseEntity<MoodLogDTO> addMoodLog(
            @PathVariable Long UserId,
            @RequestBody MoodLogDTO moodLogDTO) {
        MoodLogDTO savedLog = moodService.logMood(UserId, moodLogDTO);
        return ResponseEntity.ok(savedLog);
    }

    @GetMapping("/user/{UserId}")
    public ResponseEntity<List<MoodLogDTO>> getMoodLogsByUserId(@PathVariable Long UserId) {
        List<MoodLogDTO> logs = moodService.getMoodLogsByUser(UserId);
        return ResponseEntity.ok(logs);
    }
}

