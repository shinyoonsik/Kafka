package com.example.kafkaspring.config;

import com.example.kafkaspring.domain.MyMessage;
import com.example.kafkaspring.util.CustomJsonSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
public class KafkaProducerConfig {


    @Value("${kafka.bootstrap-server}")
    private String BOOTSTRAP_SERVER;

    @Bean
    public ProducerFactory<String, String> producerFactory(){
        HashMap<String, Object> config = new HashMap<>();
        config.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        config.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    // 이벤트(데이터) 발송(produce, publish)에 필요한 객체: KafkaTemplate
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, MyMessage> myMessageProducerFactory(){
        HashMap<String, Object> config = new HashMap<>();
        config.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        config.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(VALUE_SERIALIZER_CLASS_CONFIG, CustomJsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, MyMessage> myMessageKafkaTemplate(){
        return new KafkaTemplate<>(myMessageProducerFactory());
    }
}
