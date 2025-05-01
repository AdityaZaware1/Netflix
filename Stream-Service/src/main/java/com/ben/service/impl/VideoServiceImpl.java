package com.ben.service.impl;

import com.ben.dto.UserDto;
import com.ben.entity.Video;
import com.ben.enums.Role;
import com.ben.external.UserService;
import com.ben.kafka.KafkaProducer;
import com.ben.repo.VideoRepo;
import com.ben.service.VideoService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepo videoRepo;
    private final UserService userService;
    private final KafkaProducer kafkaProducer;

    @Value("${files.video}")
    String DIR;

    @PostConstruct
    public void init() {

        File file = new File(DIR);

        if(!file.exists()) {
            file.mkdir();
        }
    }

    @Override
    public Video save(Video video, MultipartFile file, Long userId) {

        UserDto userDto = userService.getUserById(userId);

        if(userDto.getRole() != Role.ADMIN) {
            throw new RuntimeException("Unauthorized");
        }

        try {

            // getting original file name
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();

            //cleaning file name
            String cleanFile = StringUtils.cleanPath(fileName);

            //cleaning folder
            String cleanFolder = StringUtils.cleanPath(DIR);

            //creating folder path
            Path path = Paths.get(cleanFolder, cleanFile);

            // copy file to the folder

            Files.copy(inputStream,path, StandardCopyOption.REPLACE_EXISTING);

            video.setContentType(contentType);
            video.setFilePath(path.toString());

            // metadata save
            return videoRepo.save(video);

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Video getVideo(Long id) {
        Video video = videoRepo.findById(id).get();

        if (video == null) {
            throw new RuntimeException("Video not found");
        }

        return video;
    }

    @Override
    public Video getVideoByTitle(String title) {
        Optional<Video> video = videoRepo.findByTitle(title);

        if (video.isEmpty()) {
            throw new RuntimeException("Video not found");
        }

        return video.get();
    }

    @Override
    public List<Video> getAll() {
        return videoRepo.findAll();
    }

    @Override
    public Video getVideoById(Long id) {
        Video video = videoRepo.findById(id).get();

        if (video == null) {
            throw new RuntimeException("Video not found");
        }

        return video;
    }
}
