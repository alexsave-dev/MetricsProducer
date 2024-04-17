package com.example.producer.service;

import com.example.producer.dto.MetricRequestDto;
import com.example.producer.dto.MetricResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MetricService {

    MetricResponseDto transfer(MetricRequestDto transferRequestDto) throws JsonProcessingException;
}
