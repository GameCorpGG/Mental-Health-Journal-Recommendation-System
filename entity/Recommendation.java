package com.Rachit.mental_health.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RecommendationId;

    private LocalDate recommendationDate;

    // Many-to-One with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    // Many-to-One with Content
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "content_id")
    private Content content;
}

