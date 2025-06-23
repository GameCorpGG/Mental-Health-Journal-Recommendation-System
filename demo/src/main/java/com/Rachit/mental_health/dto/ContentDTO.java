package com.Rachit.mental_health.dto;

import lombok.Data;

@Data
public class ContentDTO {
    private Long ContentId;
    private String title;
    private String description;
    private String url;
    private String category;
    private boolean moderated;
    private Long UserId;
}
