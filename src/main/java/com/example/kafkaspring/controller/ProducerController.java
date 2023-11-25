package com.example.kafkaspring.controller;

import com.example.kafkaspring.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
