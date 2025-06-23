package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.ContentDTO;

import java.util.List;

public interface ContentService {
    ContentDTO addContent(ContentDTO contentDTO);
    List<ContentDTO> getAllContent();
    List<ContentDTO> getContentByContentId(Long ContentID);
}

