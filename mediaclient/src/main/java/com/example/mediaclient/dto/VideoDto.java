package com.example.mediaclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {


    private String id;
    private String title;
    private String description;
    private String url;
    private int durationSeconds;
    private CreatorDto creator;
}
