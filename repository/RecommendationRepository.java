package com.Rachit.mental_health.repository;

import com.Rachit.mental_health.entity.Recommendation;
import com.Rachit.mental_health.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByUser(User user);
}
