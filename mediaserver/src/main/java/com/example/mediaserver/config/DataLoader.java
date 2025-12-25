package com.example.mediaserver.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.mediaserver.entities.Creator;
import com.example.mediaserver.repositories.CreatorRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final CreatorRepository creatorRepository;

    public DataLoader(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    @Override
    public void run(String... args) {

        if (creatorRepository.count() == 0) {

            Creator creator = Creator.builder()
                    .name("Default Creator")
                    .email("default@media.com")
                    .build();

            creatorRepository.save(creator);

            System.out.println("Creator created with id: " + creator.getId());
        }
    }
}
