package com.example.mediaserver.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String url;
    private int durationSeconds;

    @ManyToOne
    private Creator creator;
}
