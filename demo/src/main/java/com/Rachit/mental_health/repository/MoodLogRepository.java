package com.Rachit.mental_health.repository;

import com.Rachit.mental_health.entity.MoodLog;
import com.Rachit.mental_health.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoodLogRepository extends JpaRepository<MoodLog, Long> {
    List<MoodLog> findByUser(User user);
}
