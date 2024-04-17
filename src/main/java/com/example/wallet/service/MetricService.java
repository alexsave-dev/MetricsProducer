package com.example.wallet.service;

import com.example.wallet.dto.MetricRequestDto;
import com.example.wallet.dto.MetricResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MetricService {

    MetricResponseDto transfer(MetricRequestDto transferRequestDto) throws JsonProcessingException;
}
