package com.example.kafkaspring.controller;

import com.example.kafkaspring.domain.MyMessage;
import com.example.kafkaspring.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ProducerController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/publish")
    public String producerMessage(@RequestParam(value = "message") String message){
        kafkaProducerService.send(message);

        return "published message: " + message;
    }

    @GetMapping("/publish2")
    public String producerMessage2(String message){
        kafkaProducerService.sendWithCallback(message);

        return "published2 message: " + message;
    }

    @PostMapping("/publish3")
    public String producerMessage3(@RequestBody MyMessage message){
        kafkaProducerService.sendJson(message);

        return "published2 message: " + message;
    }
}
