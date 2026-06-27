package ru.zdoher.events.collector.config;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.zdoher.events.collector.config.properties.KafkaProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@ConfigurationPropertiesScan
public class AppConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public KafkaAvroSerializer kafkaAvroSerializer() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("schema.registry.url", kafkaProperties.getSchemaRegistry().getUrl());

        KafkaAvroSerializer serializer = new KafkaAvroSerializer();

        serializer.configure(configs, false);

        return serializer;
    }
}
