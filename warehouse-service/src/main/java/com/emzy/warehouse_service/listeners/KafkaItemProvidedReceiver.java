package com.emzy.warehouse_service.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaItemProvidedReceiver {

    private static final Logger log = LoggerFactory.getLogger(KafkaItemProvidedReceiver.class);

    public KafkaItemProvidedReceiver() {
    }

    @KafkaListener(topics = "items.provided")
    public void receives(final String in) {
        log.info("Received item: {}", in);
    }
}
