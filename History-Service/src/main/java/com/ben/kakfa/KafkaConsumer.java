package com.ben.kakfa;

import com.ben.dto.Video;
import com.ben.entity.History;
import com.ben.external.VideoService;
import com.ben.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final HistoryService historyService;

    @KafkaListener(topics = "history-topic", groupId = "history-group")
    public void consume(String message) {

        Long userId = Long.parseLong(message.split(",")[0]);
        Long videoId = Long.parseLong(message.split(",")[1]);

        historyService.saveHistory(userId, videoId);
    }

}
