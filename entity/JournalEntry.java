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
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long JournalId;

    private String title;

    @Column(length = 5000)
    private String content;

    private LocalDateTime entryDate;

    // Many-to-One with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}