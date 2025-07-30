package com.Rachit.mental_health.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "content", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private List<Recommendation> recommendations;
}
