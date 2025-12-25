package com.example.mediaserver.repositories;

import com.example.mediaserver.entities.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
}
