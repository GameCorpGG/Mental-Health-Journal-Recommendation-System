package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.ContentDTO;
import com.Rachit.mental_health.entity.Content;
import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.exception.InvalidInputException;
import com.Rachit.mental_health.exception.ResourceNotFoundException;
import com.Rachit.mental_health.repository.ContentRepository;
import com.Rachit.mental_health.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    @Autowired
    private final ContentRepository contentRepository;

    @Autowired
    private final UserRepository UserRepository;

    @Override
    public ContentDTO addContent(ContentDTO dto, String username) {
        User user = UserRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found with name: " + username));
        Content content = new Content();
        content.setTitle(dto.getTitle());
        content.setDescription(dto.getDescription());
        content.setCategory(dto.getCategory());
        content.setUrl(dto.getUrl());


        content.setUser(user);

        Content saved = contentRepository.save(content);

        ContentDTO response = new ContentDTO();
        response.setUserId(saved.getUser().getUserId());
        response.setTitle(saved.getTitle());
        response.setDescription(saved.getDescription());
        response.setCategory(saved.getCategory());
        response.setUrl(saved.getUrl());

        return response;
    }

    @Autowired
    private YouTubeApiService youTubeApiService;

    public ContentDTO addContentFromYouTubeURL(String url, String category, String email) {
        String videoId = extractVideoIdFromUrl(url);

        User user = UserRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        System.out.println("Extracted video ID: " + videoId);
        Content content = youTubeApiService.fetchVideoAsContent(videoId, category);
        if (content == null) {
            throw new RuntimeException("Failed to fetch video details from YouTube API.");
        }

        content.setCategory(category);
        content.setUser(user);

        Content saved = contentRepository.save(content);

        ContentDTO dto = new ContentDTO();
        dto.setUserId(saved.getUser().getUserId());
        dto.setTitle(saved.getTitle());
        dto.setDescription(saved.getDescription());
        dto.setCategory(saved.getCategory());
        dto.setUrl(saved.getUrl());

        return dto;
    }
    public String extractVideoIdFromUrl(String url) {
        try {
            URI uri = new URI(url);
            String host = uri.getHost();

            if (host.contains("youtube.com")) {
                String query = uri.getQuery();
                if (query != null) {
                    for (String param : query.split("&")) {
                        String[] pair = param.split("=");
                        if (pair.length == 2 && pair[0].equals("v")) {
                            return pair[1];
                        }
                    }
                }
            } else if (host.contains("youtu.be")) {
                // e.g., https://youtu.be/vwBnZXn_YA4?si=xyz
                String path = uri.getPath(); // "/vwBnZXn_YA4"
                return path.substring(1).split("\\?")[0]; // Remove slash and query
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("Could not extract video ID from URL");
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
    public List<ContentDTO> getContentByUserID(Long userId) {
        return contentRepository.findByUser_UserId(userId).stream().map(content -> {
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
