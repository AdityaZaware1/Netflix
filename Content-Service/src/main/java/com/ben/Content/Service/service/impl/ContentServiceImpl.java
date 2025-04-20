package com.ben.Content.Service.service.impl;

import com.ben.Content.Service.dto.UserDto;
import com.ben.Content.Service.entity.Content;
import com.ben.Content.Service.enums.Role;
import com.ben.Content.Service.external.UserService;
import com.ben.Content.Service.repo.ContentRepo;
import com.ben.Content.Service.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepo contentRepo;
    private final UserService userService;

    @Override
    public List<Content> getAllContents() {
        return contentRepo.findAll();
    }

    @Override
    public Content getContentById(Long id) {
        Content content = contentRepo.findById(id).orElse(null);

        if (content == null) {
            throw new RuntimeException("Content not found");
        }

        return content;
    }

    @Override
    public Content createContent(Content content, Long adminId) {
        UserDto userDto = userService.getUserById(adminId);

        if(userDto.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only admin can create content");
        }

        return contentRepo.save(content);
    }

    @Override
    public Content updateContent(Long id, Content content, Long adminId) {

        UserDto userDto = userService.getUserById(adminId);

        if(userDto.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only admin can create content");
        }

        Content existingContent = contentRepo.findById(id).orElse(null);

        if (existingContent == null) {
            throw new RuntimeException("Content not found");
        }

        existingContent.setTitle(content.getTitle());
        existingContent.setGenre(content.getGenre());
        existingContent.setDescription(content.getDescription());
        existingContent.setDuration(content.getDuration());
        existingContent.setReleaseDate(content.getReleaseDate());
        existingContent.setType(content.getType());

        return contentRepo.save(existingContent);
    }

    @Override
    public void deleteContent(Long id) {
        contentRepo.deleteById(id);
    }

    @Override
    public Content getContentByName(String name) {
        Content content = contentRepo.findContentByTitle(name);

        if (content == null) {
            throw new RuntimeException("Content not found");
        }

        return content;
    }
}
