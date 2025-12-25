package com.example.mediaserver.repositories;

import com.example.mediaserver.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepository extends JpaRepository<Creator, Long> {
}
