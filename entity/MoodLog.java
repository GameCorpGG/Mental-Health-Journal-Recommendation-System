package com.Rachit.mental_health.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    private LocalDateTime date;

    private String note;

    // Many-to-One with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}