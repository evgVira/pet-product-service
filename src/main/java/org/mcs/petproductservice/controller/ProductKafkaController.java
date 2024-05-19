package org.mcs.petproductservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mcs.petproductservice.dto.ProductKafkaRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-service/kafka-event")
@Slf4j
public class ProductKafkaController {

    private final KafkaTemplate<String, ProductKafkaRequestDto> kafkaTemplate;

    @Value("${application.kafka.topic}")
    private String topicName;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendEventToKafka(@RequestBody ProductKafkaRequestDto productKafkaRequestDto) {
        var value = kafkaTemplate.send(topicName, productKafkaRequestDto);
        log.info("message was send : %s, %s".formatted(productKafkaRequestDto.getUniqProductId(), productKafkaRequestDto.getQuantity()));
    }
}
