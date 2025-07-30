package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.ContentDTO;
import com.Rachit.mental_health.dto.RecommendationDTO;
import com.Rachit.mental_health.entity.Content;
import com.Rachit.mental_health.entity.Recommendation;
import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.exception.ResourceNotFoundException;
import com.Rachit.mental_health.repository.ContentRepository;
import com.Rachit.mental_health.repository.MoodLogRepository;
import com.Rachit.mental_health.repository.RecommendationRepository;
import com.Rachit.mental_health.repository.UserRepository;
import com.Rachit.mental_health.config.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class RecommendationServiceImpl implements RecommendationService {

    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    private final RecommendationRepository recommendationRepository;
    private final MoodLogRepository moodLogRepository;
    private final JwtUtil jwtUtil;

    @Override
    public List<RecommendationDTO> getRecommendationsByUser(Long UserId) {
        User user = userRepository.findByUserId(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return recommendationRepository.findByUser(user).stream().map(rec -> {
            RecommendationDTO dto = new RecommendationDTO();
            dto.setRecommendationId(rec.getRecommendationId());
            dto.setUserId(rec.getUser().getUserId());
            Content content = rec.getContent();
            ContentDTO contentDTO = new ContentDTO();
            contentDTO.setContentId(content.getContentId());
            contentDTO.setTitle(content.getTitle());
            contentDTO.setDescription(content.getDescription());
            contentDTO.setCategory(content.getCategory());
            contentDTO.setUrl(content.getUrl());
            contentDTO.setUserId(content.getUser().getUserId());

            dto.setContent(contentDTO);
            dto.setRecommendationDate(rec.getRecommendationDate());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public RecommendationDTO saveRecommendation(Long UserId, RecommendationDTO dto) {
        User user = userRepository.findByUserId(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Long contentId= dto.getContent().getContentId();
        Content content = contentRepository.findByContentId(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found"));

        Recommendation recommendation = new Recommendation();
        recommendation.setUser(user);
        recommendation.setContent(content);
        recommendation.setRecommendationDate(dto.getRecommendationDate());

        Recommendation saved = recommendationRepository.save(recommendation);

        RecommendationDTO response = new RecommendationDTO();
        response.setRecommendationId(saved.getRecommendationId());
        response.setUserId(saved.getUser().getUserId());
        ContentDTO contentDTO = new ContentDTO();
        contentDTO.setContentId(content.getContentId());
        contentDTO.setTitle(content.getTitle());
        contentDTO.setDescription(content.getDescription());
        contentDTO.setCategory(content.getCategory());
        contentDTO.setUrl(content.getUrl());
        contentDTO.setUserId(content.getUser().getUserId());

        response.setContent(contentDTO);

        response.setRecommendationDate(saved.getRecommendationDate());

        return response;
    }

    public List<RecommendationDTO> recommendContentForUser(HttpServletRequest request) {

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }
        String token = header.substring(7); // remove "Bearer "
        if (!jwtUtil.validateToken(token)) {
            throw new RuntimeException("Invalid JWT token");
        }
        Long userId = jwtUtil.extractUserID(token);
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Fetch latest mood (implement this method in MoodLogRepository)
        Optional<String> latestMoodOpt = moodLogRepository.findLatestMoodByUserId(userId);
        if (latestMoodOpt.isEmpty()) {
            throw new ResourceNotFoundException("No mood logs found for user: " + userId);
        }

        String latestMood = latestMoodOpt.get();

        List<Content> matchingContent = contentRepository.findByCategoryAndStatusNot(latestMood, "REJECTED");
        if (matchingContent.isEmpty()) {
            throw new ResourceNotFoundException("No content found for mood: " + latestMood);
        }


        List<RecommendationDTO> recommendations = matchingContent.stream().map(content -> {
            ;
            Recommendation recommendation = new Recommendation();
            recommendation.setUser(user);
            recommendation.setContent(content);
            recommendation.setRecommendationDate(LocalDate.now());
            Recommendation saved = recommendationRepository.save(recommendation);

            RecommendationDTO dto = new RecommendationDTO();
            dto.setRecommendationId(saved.getRecommendationId());
            dto.setUserId(user.getUserId());
            ContentDTO contentDTO = new ContentDTO();
            contentDTO.setContentId(content.getContentId());
            contentDTO.setTitle(content.getTitle());
            contentDTO.setDescription(content.getDescription());
            contentDTO.setCategory(content.getCategory());
            contentDTO.setUrl(content.getUrl());
            contentDTO.setUserId(content.getUser().getUserId());

            dto.setContent(contentDTO);

            dto.setRecommendationDate(saved.getRecommendationDate());
            return dto;
        }).collect(Collectors.toList());

        return recommendations;
    }
}