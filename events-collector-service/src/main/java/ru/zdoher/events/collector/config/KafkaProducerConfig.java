package ru.zdoher.events.collector.config;

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import ru.zdoher.events.collector.config.properties.KafkaProperties;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private static final String REQUIRED_ACKS = "all";

    @Valid
    private final KafkaProperties kafkaProperties;

    @Bean
    ProducerFactory<String, GenericRecord> producerFactory() {
        return new DefaultKafkaProducerFactory<>(
          Map.of(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers(),
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class,
            ProducerConfig.ACKS_CONFIG, REQUIRED_ACKS,
            ProducerConfig.RETRIES_CONFIG, maxRetries(),
            ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, maxInFlightRequestsPerConnection(),
            ProducerConfig.MAX_BLOCK_MS_CONFIG, maxBlockMs(),
            KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, kafkaProperties.getSchemaRegistry().getUrl(),
            KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true
          )
        );
    }

    @Bean
    KafkaTemplate<String, GenericRecord> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    private String bootstrapServers() {
        return kafkaProperties.getBootstrapServers();
    }

    private int maxInFlightRequestsPerConnection() {
        return kafkaProperties.getProducer().getMaxInFlightRequestsPerConnection();
    }

    private int maxRetries() {
        return kafkaProperties.getProducer().getMaxRetries();
    }

    private long maxBlockMs() {
        return kafkaProperties.getProducer().getMaxBlockMs();
    }
}
