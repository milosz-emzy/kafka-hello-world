package com.emzy.factory_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "emzy.kafka")
public record KafkaConfigProps(
        String topic
) {
}
