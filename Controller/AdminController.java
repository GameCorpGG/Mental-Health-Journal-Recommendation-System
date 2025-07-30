package com.Rachit.mental_health.Controller;

import com.Rachit.mental_health.dto.ContentDTO;
import com.Rachit.mental_health.dto.UserDTO;
import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.entity.Content;
import com.Rachit.mental_health.exception.InvalidInputException;
import com.Rachit.mental_health.service.AdminService;
import com.Rachit.mental_health.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    private final ContentService contentService;

    // Fetch all users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/all_users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/find")
    public ResponseEntity<List<UserDTO>> findUser(@RequestParam String email) {
        List<UserDTO> userlist = adminService.findUser(email);
        return ResponseEntity.ok(userlist);
    }

    // Delete user by ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String email) {
        adminService.deleteUserByEmail(email);
        return ResponseEntity.ok("User deleted successfully");
    }

    // Fetch all content
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/content")
    public ResponseEntity<List<Content>> getAllContent() {
        return ResponseEntity.ok(adminService.getAllContent());
    }

    @PostMapping("/content/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContentDTO> addContentFromUI(@RequestBody Map<String, String> request, Principal principal) {
        String url = request.get("url");
        String category = request.get("category");

        if (url == null || category == null) {
            throw new InvalidInputException("URL and Category must be provided");
        }

        ContentDTO added = contentService.addContentFromYouTubeURL(url, category, principal.getName());
        return ResponseEntity.ok(added);
    }

    // Delete content by ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/content/delete")
    public ResponseEntity<String> deleteContent(@RequestParam String url) {
        adminService.deleteContentByUrl(url);
        return ResponseEntity.ok("Content deleted successfully");
    }

    // Approve content
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/content/approve")
    public ResponseEntity<Content> approveContent(@RequestParam String url) {
        return ResponseEntity.ok(adminService.approveContent(url));
    }

    // Reject content
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/content/reject")
    public ResponseEntity<Content> rejectContent(@RequestParam String url) {
        return ResponseEntity.ok(adminService.rejectContent(url));
    }
}
