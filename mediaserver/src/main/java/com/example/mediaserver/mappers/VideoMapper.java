package com.example.mediaserver.mappers;

import com.example.Video; // proto
import com.example.UploadVideoRequest;
import com.example.mediaserver.entities.Creator;
import com.example.mediaserver.entities.VideoEntity; // JPA

import org.springframework.stereotype.Component;

@Component
public class VideoMapper {

    public VideoEntity toEntity(UploadVideoRequest request, Creator creator) {
        return VideoEntity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .url(request.getUrl())
                .durationSeconds(request.getDurationSeconds())
                .creator(creator)
                .build();
    }

    public Video toProto(VideoEntity entity) {
        return Video.newBuilder()
                .setId(entity.getId().toString())
                .setTitle(entity.getTitle())
                .setDescription(entity.getDescription())
                .setUrl(entity.getUrl())
                .setDurationSeconds(entity.getDurationSeconds())
                .setCreator(
                        com.example.Creator.newBuilder()
                                .setId(entity.getCreator().getId().toString())
                                .setName(entity.getCreator().getName())
                                .setEmail(entity.getCreator().getEmail())
                                .build()
                )
                .build();
    }
}
