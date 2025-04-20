package com.ben.Content.Service.service;

import com.ben.Content.Service.entity.Content;

import java.util.List;

public interface ContentService {

    public List<Content> getAllContents();
    public Content getContentById(Long id);
    public Content createContent(Content content, Long adminId);
    public Content updateContent(Long id, Content content,  Long adminId);
    public void deleteContent(Long id);

    Content getContentByName(String name);
}
