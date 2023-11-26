package com.example.kafkaspring.service;

import com.example.kafkaspring.domain.MyMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "${kafka.topic}")
    public void listenMessage(String jsonMessage){
        try {
            MyMessage myMessage = objectMapper.readValue(jsonMessage, MyMessage.class);
            log.info("consuming message: {}", myMessage.getMessage());
        } catch (JsonProcessingException e) {
            log.error("error 발생: " + e.getMessage(), e);
        }
    }
}
