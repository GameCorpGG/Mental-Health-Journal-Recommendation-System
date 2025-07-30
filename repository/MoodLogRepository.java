package com.Rachit.mental_health.repository;

import com.Rachit.mental_health.entity.MoodLog;
import com.Rachit.mental_health.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MoodLogRepository extends JpaRepository<MoodLog, Long> {
    List<MoodLog> findByUser(User user);
    @Query("SELECT m.mood FROM MoodLog m WHERE m.user.userId = :userId ORDER BY m.date DESC LIMIT 1")
    Optional<String> findLatestMoodByUserId(@Param("userId") Long userId);

}
