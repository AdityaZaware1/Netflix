package com.ben.external;

import com.ben.dto.Video;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "STREAM-SERVICE", url = "${video.service.url}")
public interface VideoService {

    @GetMapping("/get/{id}")
    public Video getById(@PathVariable Long id);
}
