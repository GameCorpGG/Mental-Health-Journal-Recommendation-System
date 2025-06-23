package com.Rachit.mental_health.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MoodLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MoodLogId;

    private String mood; // e.g., Happy, Anxious, Depressed

    private LocalDate date;

    private String note;

    // Many-to-One with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}