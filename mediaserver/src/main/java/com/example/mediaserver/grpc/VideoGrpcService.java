package com.example.mediaserver.grpc;

import com.example.Video;
import com.example.VideoIdRequest;
import com.example.UploadVideoRequest;
import com.example.VideoServiceGrpc;


import com.example.mediaserver.entities.Creator;
import com.example.mediaserver.entities.VideoEntity; // JPAimport com.example.mediaserver.entities.VideoEntity;
import com.example.mediaserver.mappers.VideoMapper;
import com.example.mediaserver.repositories.VideoRepository;
import com.example.mediaserver.repositories.CreatorRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class VideoGrpcService extends VideoServiceGrpc.VideoServiceImplBase {

    private final VideoRepository videoRepository;
    private final CreatorRepository creatorRepository;
    private final VideoMapper videoMapper;

    public VideoGrpcService(
            VideoRepository videoRepository,
            CreatorRepository creatorRepository,
            VideoMapper videoMapper
    ) {
        this.videoRepository = videoRepository;
        this.creatorRepository = creatorRepository;
        this.videoMapper = videoMapper;
    }

    @Override
    public void uploadVideo(UploadVideoRequest request, StreamObserver<Video> responseObserver) {
        var creator = request.getCreator().getId().isEmpty()
                ? creatorRepository.save(Creator.builder()
                .name(request.getCreator().getName())
                .email(request.getCreator().getEmail())
                .build())
                : creatorRepository.findById(Long.parseLong(request.getCreator().getId()))
                .orElseThrow(() -> new RuntimeException("Creator not found"));


        var videoEntity = videoMapper.toEntity(request, creator);
        videoRepository.save(videoEntity);

        Video response = videoMapper.toProto(videoEntity);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getVideo(VideoIdRequest request, StreamObserver<Video> responseObserver) {
        VideoEntity videoEntity = videoRepository.findById(Long.parseLong(request.getId()))
                .orElseThrow(() -> new RuntimeException("Video not found"));

        Video response = videoMapper.toProto(videoEntity);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
