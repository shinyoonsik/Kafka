package com.example.kafkaspring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("ALL")
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final String TOPIC_NAME = "topic3";

    private final KafkaTemplate kafkaTemplate;

    public void send(String message){
        kafkaTemplate.send(TOPIC_NAME, message);
        log.info("produce message: {}", message);
    }

    public void sendWithCallback(String message){
        CompletableFuture<SendResult<String, String>> result = kafkaTemplate.send(TOPIC_NAME, message);

        result.thenAccept(resultData -> {
            // 성공 callback
            System.out.println("Message sent successfully = " + resultData.getRecordMetadata().topic());
        }).exceptionally(ex -> {
            System.out.println("Message failed to send: " + ex);
            return null;
        });

    }
}
