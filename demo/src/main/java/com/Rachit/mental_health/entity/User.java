package com.Rachit.mental_health.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String role; // e.g., ROLE_USER, ROLE_ADMIN

    private LocalDate Createdate; // Date of account creation

    // One-to-Many with JournalEntry
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<JournalEntry> journalEntries;

    // One-to-Many with MoodLog
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MoodLog> moodLogs;

    // One-to-Many with Content recommendations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Content> recommendedContent;
}
