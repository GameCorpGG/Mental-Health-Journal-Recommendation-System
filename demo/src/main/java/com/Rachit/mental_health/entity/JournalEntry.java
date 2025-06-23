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
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long JournalId;

    private String title;

    @Column(length = 5000)
    private String content;

    private LocalDate entryDate;

    // Many-to-One with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}