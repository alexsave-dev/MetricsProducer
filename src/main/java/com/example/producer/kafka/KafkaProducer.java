package com.example.producer.kafka;
import com.example.producer.dto.MetricRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class KafkaProducer {

    @Value("${kafka.topic.name}")
    private String TOPIC_NAME;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(MetricRequestDto transferRequestDto) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(transferRequestDto);
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME,
                UUID.randomUUID().toString(),
                jsonString);
        kafkaTemplate.send(record);
    }

}