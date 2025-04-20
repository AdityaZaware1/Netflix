package com.ben.Content.Service.controller;

import com.ben.Content.Service.entity.Content;
import com.ben.Content.Service.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService contentService;

    @PostMapping("/add/{id}")
    public ResponseEntity<Content> addContent(@RequestBody Content content, @PathVariable Long adminId) {
        return ResponseEntity.ok(contentService.createContent(content, adminId));
    }

    @PostMapping("/update/{contentId}/{adminId}")
    public ResponseEntity<Content> updateContent(@RequestBody Content content,
                                                 @PathVariable Long contentId,
                                                 @PathVariable Long adminId) {
        return ResponseEntity.ok(contentService.updateContent(contentId, content, adminId));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Content> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable Long content) {
        return ResponseEntity.ok(contentService.getContentById(content));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Content> getContentByName(@PathVariable String name) {
        return ResponseEntity.ok(contentService.getContentByName(name));
    }
}
