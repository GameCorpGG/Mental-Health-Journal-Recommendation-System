package com.Rachit.mental_health.service;

import com.Rachit.mental_health.entity.Content;
import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.exception.ResourceNotFoundException;
import com.Rachit.mental_health.repository.ContentRepository;
import com.Rachit.mental_health.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long UserId) {
        User user = userRepository.findByUserId(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID" + UserId));
        userRepository.delete(user);
    }

    @Override
    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }

    @Override
    public void deleteContentById(Long ContentId) {
        Content content = contentRepository.findByContentId(ContentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with ID" + ContentId));
        contentRepository.delete(content);
    }

    @Override
    public Content approveContent(Long ContentId) {
        Content content = contentRepository.findByContentId(ContentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with ID" + ContentId));
        content.setStatus("APPROVED");
        return contentRepository.save(content);
    }

    @Override
    public Content rejectContent(Long ContentId) {
        Content content = contentRepository.findByContentId(ContentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with ID" + ContentId));
        content.setStatus("REJECTED");
        return contentRepository.save(content);
    }
}

