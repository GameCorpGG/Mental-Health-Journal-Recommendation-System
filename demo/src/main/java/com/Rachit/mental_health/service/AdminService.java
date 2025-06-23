package com.Rachit.mental_health.service;

import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.entity.Content;

import java.util.List;

public interface AdminService {
    List<User> getAllUsers();
    void deleteUserById(Long UserId);

    // Content Moderation
    List<Content> getAllContent();
    void deleteContentById(Long ContentId);
    Content approveContent(Long ContentId);
    Content rejectContent(Long ContentId);
}

