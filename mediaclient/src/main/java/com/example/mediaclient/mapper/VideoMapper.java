package com.example.mediaclient.mapper;

import com.example.Video;
import com.example.mediaclient.dto.CreatorDto;
import com.example.mediaclient.dto.VideoDto;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {

    public VideoDto fromProtoToDto(Video video) {
        CreatorDto creatorDto = new CreatorDto(
                video.getCreator().getId(),
                video.getCreator().getName(),
                video.getCreator().getEmail()
        );

        return new VideoDto(
                video.getId(),
                video.getTitle(),
                video.getDescription(),
                video.getUrl(),
                video.getDurationSeconds(),
                creatorDto
        );
    }
}
