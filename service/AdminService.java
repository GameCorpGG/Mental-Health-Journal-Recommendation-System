package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.UserDTO;
import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.entity.Content;

import java.util.List;

public interface AdminService {
    List<User> getAllUsers();
    void deleteUserByEmail(String email);
    List<UserDTO> findUser(String email);
    List<Content> getAllContent();
    void deleteContentByUrl(String url);
    Content approveContent(String url);
    Content rejectContent(String url);
}

