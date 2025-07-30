package com.Rachit.mental_health.Controller;

import com.Rachit.mental_health.dto.RecommendationDTO;
import com.Rachit.mental_health.service.RecommendationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/get/{UserId}")
    public ResponseEntity<List<RecommendationDTO>> getRecommendationsByUser(@PathVariable Long UserId) {
        List<RecommendationDTO> recommendations = recommendationService.getRecommendationsByUser(UserId);
        return ResponseEntity.ok(recommendations);
    }

    @PostMapping("/{UserId}")
    public ResponseEntity<RecommendationDTO> saveRecommendation(
            @PathVariable Long UserId,
            @RequestBody RecommendationDTO dto) {
        RecommendationDTO saved = recommendationService.saveRecommendation(UserId, dto);
        return ResponseEntity.ok(saved);
    }
    @GetMapping("/recommend")
    public ResponseEntity<List<RecommendationDTO>> recommendContentForUser(HttpServletRequest request) {
        List<RecommendationDTO> recommendation = recommendationService.recommendContentForUser(request);
        return ResponseEntity.ok(recommendation);
    }
}
