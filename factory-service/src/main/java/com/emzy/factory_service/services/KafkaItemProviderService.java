package com.emzy.factory_service.services;

import com.emzy.factory_service.config.KafkaConfigProps;
import com.emzy.factory_service.model.Item;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaItemProviderService {

    private static final Logger log = LoggerFactory.getLogger(KafkaItemProviderService.class);
    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaConfigProps kafkaConfigProps;

    public KafkaItemProviderService(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate, KafkaConfigProps kafkaConfigProps) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfigProps = kafkaConfigProps;
    }

    public void provide() {
        String topic = kafkaConfigProps.topic();
        log.info("Topic is: {}", topic);
        try {
            for (int i = 0; i < 10; i++) {
                Item item = new Item(
                        (long) i,
                        "item-name " + i,
                        LocalDateTime.now()
                );
                final String payload = objectMapper.writeValueAsString(item);

                kafkaTemplate.send(topic, payload);

                log.info("Item: {} has been sent.", item);

                Thread.sleep(1000);
            }
        } catch (JsonProcessingException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
