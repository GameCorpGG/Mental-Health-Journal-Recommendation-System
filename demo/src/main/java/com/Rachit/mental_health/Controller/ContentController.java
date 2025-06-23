package com.Rachit.mental_health.Controller;

import com.Rachit.mental_health.dto.ContentDTO;
import com.Rachit.mental_health.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/{category}")
    public ResponseEntity<List<ContentDTO>> getContentByContentID(@PathVariable Long ContentID) {
        List<ContentDTO> contentList = contentService.getContentByContentId(ContentID);
        return ResponseEntity.ok(contentList);
    }

    @GetMapping("/{get_all}")
    public ResponseEntity<List<ContentDTO>> getAllContent() {
        List<ContentDTO> contentList = contentService.getAllContent();
        return ResponseEntity.ok(contentList);
    }

    @PostMapping("/{UserId}")
    public ResponseEntity<ContentDTO> addContent( @RequestBody ContentDTO dto) {
        ContentDTO created = contentService.addContent(dto);
        return ResponseEntity.ok(created);
    }
}

