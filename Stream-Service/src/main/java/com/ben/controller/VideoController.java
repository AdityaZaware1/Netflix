package com.ben.controller;

import com.ben.entity.Video;
import com.ben.response.Response;
import com.ben.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/upload")
    public ResponseEntity<?> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) {

        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);

        Video save = videoService.save(video, file);

        if (save != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(video);
        }
         return ResponseEntity
                 .status(HttpStatus.BAD_REQUEST)
                 .body(Response.builder()
                         .message("Failed to upload video")
                         .success(false)
                         .build());
    }

    @GetMapping("/stream/{id}")
    public ResponseEntity<?> stream(
            @PathVariable Long id) {
        Video video = videoService.getVideo(id);

        String contentType = video.getContentType();
        String filePath = video.getFilePath();

        Resource resource = new FileSystemResource(filePath);

        if (contentType == null) {
            contentType = "application/octet-stream";
        }


        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    // stream video in chunks

    @GetMapping("/stream/range/{videoId}")
    public ResponseEntity<?> streamVideoRange(
            @PathVariable Long videoId,
            @RequestHeader(value = "Range", required = false) String range) {

        Video video = videoService.getVideo(videoId);
        Path path = Paths.get(video.getFilePath());

        Resource resource = new FileSystemResource(path);

        String contentType = video.getContentType();

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        long fileLength = path.toFile().length();

        if(range == null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        }

        long rangeStart;
        long rangeEnd;

        String ranges[] = range.replace("bytes=", "").split("-");

        rangeStart = Long.parseLong(ranges[0]);

        if(ranges.length > 1) {
            rangeEnd = Long.parseLong(ranges[1]);
        } else {
            rangeEnd = fileLength - 1;
        }

        if(rangeEnd > fileLength - 1) {
            rangeEnd = fileLength - 1;
        }

        InputStream inputStream;

        try {

            inputStream = Files.newInputStream(path);
            inputStream.skip(rangeStart);


        }catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        long contentLength = rangeEnd - rangeStart + 1;

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + fileLength);
        httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
        httpHeaders.add("Pragma", "no-cache");
        httpHeaders.add("Expires", "0");
        httpHeaders.add("X-Content-Type-Options", "nosniff");

        httpHeaders.setContentLength(contentLength);

        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
                .headers(httpHeaders)
                .contentType(MediaType.parseMediaType(contentType))
                .body(new InputStreamResource(inputStream));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(videoService.getAll());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<?> getByTitle(@PathVariable String title) {
        return ResponseEntity.ok(videoService.getVideoByTitle(title));
    }


}
