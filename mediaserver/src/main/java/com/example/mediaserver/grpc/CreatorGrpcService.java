package com.example.mediaserver.grpc;

import com.example.Creator;
import com.example.CreatorIdRequest;
import com.example.CreatorServiceGrpc;
import com.example.VideoStream;
import com.example.mediaserver.repositories.CreatorRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.stream.Collectors;

@GrpcService
public class CreatorGrpcService extends CreatorServiceGrpc.CreatorServiceImplBase {

    private final CreatorRepository creatorRepository;

    public CreatorGrpcService(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    @Override
    public void getCreator(CreatorIdRequest request,
                           StreamObserver<Creator> responseObserver) {

        var creatorEntity = creatorRepository.findById(Long.parseLong(request.getId()))
                .orElseThrow(() -> new RuntimeException("Creator not found"));

        Creator response = Creator.newBuilder()
                .setId(creatorEntity.getId().toString())
                .setName(creatorEntity.getName())
                .setEmail(creatorEntity.getEmail())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getCreatorVideos(CreatorIdRequest request,
                                 StreamObserver<VideoStream> responseObserver) {

        var creator = creatorRepository.findById(Long.parseLong(request.getId()))
                .orElseThrow(() -> new RuntimeException("Creator not found"));

        var videos = creator.getVideos().stream()
                .map(v -> com.example.Video.newBuilder()
                        .setId(v.getId().toString())
                        .setTitle(v.getTitle())
                        .setDescription(v.getDescription())
                        .setUrl(v.getUrl())
                        .setDurationSeconds(v.getDurationSeconds())
                        .build())
                .collect(Collectors.toList());

        VideoStream response = VideoStream.newBuilder()
                .addAllVideos(videos)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
