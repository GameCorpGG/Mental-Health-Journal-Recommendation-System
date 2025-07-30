package com.Rachit.mental_health.Controller;

import com.Rachit.mental_health.dto.ContentDTO;
import com.Rachit.mental_health.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<ContentDTO>> getContentByUserID(@PathVariable Long userId) {
        List<ContentDTO> contentList = contentService.getContentByUserID(userId);
        return ResponseEntity.ok(contentList);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ContentDTO>> getAllContent() {
        List<ContentDTO> contentList = contentService.getAllContent();
        return ResponseEntity.ok(contentList);
    }

    @PostMapping("/add")
    public ResponseEntity<ContentDTO> addContent(@RequestBody ContentDTO dto, Authentication authentication) {
        String username = authentication.getName();
        ContentDTO created = contentService.addContent(dto, username);
        return ResponseEntity.ok(created);
    }
}

