package com.Rachit.mental_health.service;

import com.Rachit.mental_health.dto.UserDTO;
import com.Rachit.mental_health.entity.Content;
import com.Rachit.mental_health.entity.User;
import com.Rachit.mental_health.exception.ResourceNotFoundException;
import com.Rachit.mental_health.repository.ContentRepository;
import com.Rachit.mental_health.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email" + email));
        userRepository.delete(user);
    }

    @Override
    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }

    @Override
    public void deleteContentByUrl(String url) {
        Content content = contentRepository.findByUrl(url)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with url" + url));
        contentRepository.delete(content);
    }

    @Override
    public List<UserDTO> findUser(String email) {
        return userRepository.findByEmail(email).stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setName(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Content approveContent(String url) {
        Content content = contentRepository.findByUrl(url)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with url" + url));
        content.setStatus("APPROVED");
        return contentRepository.save(content);
    }

    @Override
    public Content rejectContent(String url) {
        Content content = contentRepository.findByUrl(url)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with url" + url));
        content.setStatus("REJECTED");
        return contentRepository.save(content);
    }
}

