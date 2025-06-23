package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.RecommendationDTO;
import com.Rachit.mental_health.entity.Content;
import com.Rachit.mental_health.entity.Recommendation;
import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.exception.ResourceNotFoundException;
import com.Rachit.mental_health.repository.ContentRepository;
import com.Rachit.mental_health.repository.RecommendationRepository;
import com.Rachit.mental_health.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    private final RecommendationRepository recommendationRepository;

    @Override
    public List<RecommendationDTO> getRecommendationsByUser(Long UserId) {
        User user = userRepository.findByUserId(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return recommendationRepository.findByUser(user).stream().map(rec -> {
            RecommendationDTO dto = new RecommendationDTO();
            dto.setRecommendationId(rec.getRecommendationId());
            dto.setUserId(rec.getUser().getUserId());
            dto.setContentId(rec.getContent().getContentId());
            dto.setRecommendationDate(rec.getRecommendationDate());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public RecommendationDTO saveRecommendation(Long UserId, RecommendationDTO dto) {
        User user = userRepository.findByUserId(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Content content = contentRepository.findByContentId(dto.getContentId())
                .orElseThrow(() -> new ResourceNotFoundException("Content not found"));

        Recommendation recommendation = new Recommendation();
        recommendation.setUser(user);
        recommendation.setContent(content);
        recommendation.setRecommendationDate(dto.getRecommendationDate());

        Recommendation saved = recommendationRepository.save(recommendation);

        RecommendationDTO response = new RecommendationDTO();
        response.setRecommendationId(saved.getRecommendationId());
        response.setUserId(saved.getUser().getUserId());
        response.setContentId(saved.getContent().getContentId());
        response.setRecommendationDate(saved.getRecommendationDate());

        return response;
    }
}
