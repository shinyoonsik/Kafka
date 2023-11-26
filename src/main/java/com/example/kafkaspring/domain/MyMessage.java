package com.example.kafkaspring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

// POJO클래스 == 기본 자바 클래스
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyMessage {
    @JsonProperty
    private String name;

    @JsonProperty
    private String message;
}
