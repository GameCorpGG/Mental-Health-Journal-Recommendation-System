package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.RecommendationDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface RecommendationService {
    List<RecommendationDTO> getRecommendationsByUser(Long UserId);
    RecommendationDTO saveRecommendation(Long UserId, RecommendationDTO dto);
    List<RecommendationDTO> recommendContentForUser(HttpServletRequest request);
}
