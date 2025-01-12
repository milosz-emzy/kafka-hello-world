package com.emzy.factory_service;

import com.emzy.factory_service.config.KafkaConfigProps;
import com.emzy.factory_service.services.KafkaItemProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties(KafkaConfigProps.class)
public class FactoryServiceApplication {

	@Autowired
	KafkaItemProviderService kafkaItemProviderService;

	public static void main(String[] args) {
		SpringApplication.run(FactoryServiceApplication.class, args);
	}

    public void start() {
        kafkaItemProviderService.provide();
    }

	@Bean
    @Profile("!test")
	public ApplicationRunner runner() {
		return args -> {
            start();
        };
	}

}
