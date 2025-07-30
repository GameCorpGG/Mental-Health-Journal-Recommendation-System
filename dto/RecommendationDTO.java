package com.Rachit.mental_health.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecommendationDTO {
    private Long RecommendationId;
    private Long UserId;
    private ContentDTO content;
    private LocalDate recommendationDate;
}
