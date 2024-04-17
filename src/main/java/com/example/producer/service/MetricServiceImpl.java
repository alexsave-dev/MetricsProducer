package com.example.producer.service;

import com.example.producer.dto.MetricRequestDto;
import com.example.producer.dto.MetricResponseDto;
import com.example.producer.dto.ResponseStatus;
import com.example.producer.kafka.KafkaProducer;
import com.example.producer.service.exception.MetricServiceException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MetricServiceImpl implements MetricService {
    private final KafkaProducer kafkaProducer;

    public MetricServiceImpl(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public MetricResponseDto transfer(MetricRequestDto metricRequestDto)  {
        try {
            if (isGreaterThan(metricRequestDto.getProcessorUsagePercent(), 100) ||
                    isGreaterThan(metricRequestDto.getMemoryUsagePercent(), 100) ||
                    isGreaterThan(metricRequestDto.getDiskUsagePercent(), 100)) {
                throw new MetricServiceException("Percentage values must be less or equal to 100");
            }
            kafkaProducer.sendMessage(metricRequestDto);
            return MetricResponseDto.builder()
                    .status(ResponseStatus.SUCCESS)
                    .build();
        } catch (Exception e) {
            return MetricResponseDto.builder()
                    .status(ResponseStatus.ERROR)
                    .message(e.getMessage())
                    .build();
        }
    }

    private boolean isGreaterThan(BigDecimal value, int limit) {
        return (value.compareTo(BigDecimal.valueOf(limit)) > 0);
    }
}
