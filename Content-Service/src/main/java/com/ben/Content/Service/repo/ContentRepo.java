package com.ben.Content.Service.repo;

import com.ben.Content.Service.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepo extends JpaRepository<Content, Long> {
    Content findContentByTitle(String title);
}
