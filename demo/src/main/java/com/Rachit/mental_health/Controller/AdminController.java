package com.Rachit.mental_health.Controller;

import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.entity.Content;
import com.Rachit.mental_health.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // Fetch all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    // Delete user by ID
    @DeleteMapping("/users/{UserId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long UserId) {
        adminService.deleteUserById(UserId);
        return ResponseEntity.ok("User deleted successfully");
    }

    // Fetch all content
    @GetMapping("/content")
    public ResponseEntity<List<Content>> getAllContent() {
        return ResponseEntity.ok(adminService.getAllContent());
    }

    // Delete content by ID
    @DeleteMapping("/content/{ContentId}")
    public ResponseEntity<String> deleteContent(@PathVariable Long ContentId) {
        adminService.deleteContentById(ContentId);
        return ResponseEntity.ok("Content deleted successfully");
    }

    // Approve content
    @PostMapping("/content/{ContentId}/approve")
    public ResponseEntity<Content> approveContent(@PathVariable Long ContentId) {
        return ResponseEntity.ok(adminService.approveContent(ContentId));
    }

    // Reject content
    @PostMapping("/content/{ContentId}/reject")
    public ResponseEntity<Content> rejectContent(@PathVariable Long ContentId) {
        return ResponseEntity.ok(adminService.rejectContent(ContentId));
    }
}
