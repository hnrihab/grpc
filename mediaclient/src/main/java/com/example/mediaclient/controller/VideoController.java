package com.example.mediaclient.controller;

import com.example.mediaclient.dto.VideoDto;
import com.example.mediaclient.service.VideoServiceClient;
import com.example.UploadVideoRequest;
import com.example.Creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

    @Autowired
    private VideoServiceClient videoService;

    @PostMapping("/addVideo")
    public VideoDto uploadVideo() {
        // Création du creator
        Creator creator = Creator.newBuilder()
                .setName("Xproce")
                .setEmail("hirchoua.badr@gmail.com")
                .setId("2")
                .build();

        // Création de la requête UploadVideoRequest
        UploadVideoRequest request = UploadVideoRequest.newBuilder()
                .setTitle("grpc 101")
                .setDescription("The gRPC 101 is an introductory course to master Grpc")
                .setUrl("https://github.com/badrhr/gRPC101")
                .setDurationSeconds(380)
                .setCreator(creator)
                .build();

        // Appel du service gRPC
        return videoService.uploadVideo(request);
    }
}
