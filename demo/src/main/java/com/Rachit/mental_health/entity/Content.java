package com.Rachit.mental_health.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;

    private String title;

    private String category; // e.g., Anxiety, Sleep, Depression

    private String url; // URL to the content (video, article, etc.)

    @Column(length = 5000)
    private String description;

    private String author;

    private String status = "PENDING"; // Default status is PENDING

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
