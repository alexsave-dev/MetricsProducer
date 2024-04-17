package com.example.producer.controller;

import com.example.producer.dto.MetricRequestDto;
import com.example.producer.dto.MetricResponseDto;
import com.example.producer.service.MetricService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricController {
    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @PostMapping("/metrics")
    public ResponseEntity<MetricResponseDto> sendMessageToKafka(@RequestBody MetricRequestDto metricRequestDto) throws JsonProcessingException {
        MetricResponseDto responseBody = metricService.transfer(metricRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
