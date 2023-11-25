package com.example.kafkaspring.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@Configuration
public class KafkaProducerConfig {

    private static final String BOOTSTRAP_SERVER = "10.10.10.237:9092";


    @Bean
    public ProducerFactory<String, String> getProducerFactory(){
        HashMap<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    // KafkaTemplate: 메시지 발송할때 사용하는 모듈
    @Bean
    public KafkaTemplate<String, String> getKafkaTemplate(){
        return new KafkaTemplate<>(getProducerFactory());
    }
}
