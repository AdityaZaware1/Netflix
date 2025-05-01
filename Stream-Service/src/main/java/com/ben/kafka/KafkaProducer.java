package com.ben.kafka;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    public final String TOPIC = "subscription_topic";

    public KafkaTemplate<String, String> kafkaTemplate;

    public void send(Long userId, Long videoId) {
        String message = userId + "," + videoId;
        kafkaTemplate.send(TOPIC, message);
    }
}
