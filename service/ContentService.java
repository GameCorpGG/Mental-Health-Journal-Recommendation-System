package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.ContentDTO;

import java.util.List;

public interface ContentService {
    ContentDTO addContent(ContentDTO contentDTO, String username);
    ContentDTO addContentFromYouTubeURL(String url, String category, String email);
    List<ContentDTO> getAllContent();
    List<ContentDTO> getContentByUserID(Long userId);
}

