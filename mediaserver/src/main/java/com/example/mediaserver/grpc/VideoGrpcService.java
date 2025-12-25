package com.example.mediaserver.grpc;

import com.example.UploadVideoRequest;
import com.example.VideoServiceGrpc;
import com.example.mediaserver.mappers.VideoMapper;
import com.example.mediaserver.repositories.CreatorRepository;
import com.example.mediaserver.repositories.VideoRepository;
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
    public void uploadVideo(UploadVideoRequest request,
                            StreamObserver<com.example.Video> responseObserver) {

        // 1️⃣ récupérer le creator
        var creator = creatorRepository.findById(
                Long.parseLong(request.getCreator().getId())
        ).orElseThrow(() -> new RuntimeException("Creator not found"));

        // 2️⃣ mapper request → entity
        var videoEntity = videoMapper.toEntity(request, creator);

        // 3️⃣ save
        videoRepository.save(videoEntity);

        // 4️⃣ mapper entity → proto
        var response = videoMapper.toProto(videoEntity);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
