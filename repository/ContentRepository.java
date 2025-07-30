package com.Rachit.mental_health.repository;

import com.Rachit.mental_health.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long> {
    Optional<Content> findByContentId(Long contentId);
    List<Content> findByUser_UserId(Long userId);
    List<Content> findByCategoryAndStatusNot(String category, String status);
    Optional<Content> findByUrl(String url);
}