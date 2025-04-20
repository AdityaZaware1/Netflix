package com.ben.repo;


import com.ben.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepo extends JpaRepository<Video, Long> {

    Optional<Video> findByTitle(String title);
}
