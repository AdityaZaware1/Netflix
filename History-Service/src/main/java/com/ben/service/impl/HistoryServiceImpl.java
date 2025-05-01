package com.ben.service.impl;

import com.ben.dto.Video;
import com.ben.entity.History;
import com.ben.external.VideoService;
import com.ben.repo.HistoryRepo;
import com.ben.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepo historyRepo;
    private final VideoService videoService;

    @Override
    public void saveHistory(Long userId, Long videoId) {
        History history = new History();

        Video video = videoService.getById(videoId);

        history.setUserId(userId);
        history.setVideo(video);


        historyRepo.save(history);
    }

    @Override
    public void deleteHistory(Long id) {
        History history = historyRepo.findById(id).get();

        if (history == null) {
            throw new RuntimeException("History not found");
        }

        historyRepo.delete(history);
    }

    @Override
    public History getHistoryByUserId(Long userId) {
        return historyRepo.findHistoriesByUserId(userId);
    }

    @Override
    public List<History> getAll() {
        return historyRepo.findAll();
    }
}
