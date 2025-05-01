package com.ben.service;

import com.ben.entity.History;

import java.util.List;

public interface HistoryService {

    public void saveHistory(Long userId, Long videoId);

    public void deleteHistory(Long id);
    public History getHistoryByUserId(Long userId);

    List<History> getAll();
}
