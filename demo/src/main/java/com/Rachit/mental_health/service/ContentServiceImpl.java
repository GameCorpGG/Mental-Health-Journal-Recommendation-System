package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.ContentDTO;
import com.Rachit.mental_health.entity.Content;
import com.Rachit.mental_health.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    @Override
    public ContentDTO addContent(ContentDTO dto) {
        Content content = new Content();
        content.setTitle(dto.getTitle());
        content.setDescription(dto.getDescription());
        content.setCategory(dto.getCategory());
        content.setUrl(dto.getUrl());

        Content saved = contentRepository.save(content);

        ContentDTO response = new ContentDTO();
        response.setUserId(saved.getUser().getUserId());
        response.setTitle(saved.getTitle());
        response.setDescription(saved.getDescription());
        response.setCategory(saved.getCategory());
        response.setUrl(saved.getUrl());

        return response;
    }

    @Override
    public List<ContentDTO> getAllContent() {
        return contentRepository.findAll().stream().map(content -> {
            ContentDTO dto = new ContentDTO();
            dto.setUserId(content.getUser().getUserId());
            dto.setTitle(content.getTitle());
            dto.setDescription(content.getDescription());
            dto.setCategory(content.getCategory());
            dto.setUrl(content.getUrl());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ContentDTO> getContentByContentId(Long ContentId) {
        return contentRepository.findByContentId(ContentId).stream().map(content -> {
            ContentDTO dto = new ContentDTO();
            dto.setUserId(content.getUser().getUserId());
            dto.setTitle(content.getTitle());
            dto.setDescription(content.getDescription());
            dto.setCategory(content.getCategory());
            dto.setUrl(content.getUrl());
            return dto;
        }).collect(Collectors.toList());
    }
}
