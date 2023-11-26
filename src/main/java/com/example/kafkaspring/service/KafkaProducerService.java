package com.example.kafkaspring.service;

import com.example.kafkaspring.domain.MyMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    @Value("${kafka.topic}")
    private String TOPIC_NAME;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, MyMessage> myMessageKafkaTemplate;


    public void send(String message){
        kafkaTemplate.send(TOPIC_NAME, message);
        log.info("produce message: {}", message);
    }

    public void sendWithCallback(String message){
        CompletableFuture<SendResult<String, String>> result = kafkaTemplate.send(TOPIC_NAME, message);

        result.thenAccept(resultData -> {
            // 성공 callback
            log.info("Message sent successfully to topic = {}", resultData.getRecordMetadata().topic());
            log.info("Message sent successfully to topic = {}", resultData.getProducerRecord().value());
        }).exceptionally(ex -> {
            // 실패 callback
            log.info("Message failed to send: {}", ex);
            return null;
        });

    }

    public void sendJson(MyMessage message){
        CompletableFuture<SendResult<String, MyMessage>> result = myMessageKafkaTemplate.send(TOPIC_NAME, message);
        log.info("produce message: {}", message);
    }
}
