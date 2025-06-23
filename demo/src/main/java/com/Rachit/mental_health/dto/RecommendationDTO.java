package com.Rachit.mental_health.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecommendationDTO {
    private Long RecommendationId;
    private Long UserId;
    private Long ContentId;
    private LocalDate recommendationDate;
}
