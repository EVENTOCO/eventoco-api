package com.upao.sit.eventocoservice.model.dto;

public class VideoTutorialDTO {
    private String videoUrl;

    public VideoTutorialDTO(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}