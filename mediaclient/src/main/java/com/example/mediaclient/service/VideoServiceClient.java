package com.example.mediaclient.service;

import com.example.mediaclient.dto.VideoDto;
import com.example.mediaclient.mapper.VideoMapper;
import com.example.UploadVideoRequest;
import com.example.Video;
import com.example.VideoServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceClient {

    @GrpcClient("mediaserver")
    private VideoServiceGrpc.VideoServiceBlockingStub stub;

    private final VideoMapper videoMapper;

    public VideoServiceClient(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    public VideoDto uploadVideo(UploadVideoRequest request) {
        Video videoProto = stub.uploadVideo(request);
        return videoMapper.fromProtoToDto(videoProto);
    }
}
