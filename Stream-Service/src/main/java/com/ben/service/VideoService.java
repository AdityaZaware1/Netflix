package com.ben.service;

import com.ben.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {

    Video save(Video video, MultipartFile file);

    Video getVideo(Long id);

    Video getVideoByTitle(String title);

    List<Video> getAll();
}
