package ru.zdoher.events.collector.config;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import lombok.RequiredArgsConstructor;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import ru.zdoher.events.collector.config.properties.KafkaProperties;
import com.proselyte.platform.events.avro.DeviceEvent;

import jakarta.validation.Valid;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    public static final String DEVICE_EVENT_LISTENER_CONTAINER_FACTORY =
      "deviceEventKafkaListenerContainerFactory";

    private static final String READ_COMMITTED = "read_committed";
    private static final String READ_FROM_EARLIEST_OFFSET = "earliest";

    @Valid
    private final KafkaProperties kafkaProperties;

    @Bean(name = DEVICE_EVENT_LISTENER_CONTAINER_FACTORY)
    ConcurrentKafkaListenerContainerFactory<String, DeviceEvent>
    deviceEventKafkaListenerContainerFactory() {
        return createContainerFactory(deviceEventConsumerFactory());
    }

    @Bean
    ConsumerFactory<String, DeviceEvent> deviceEventConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(defaultConsumerConfig());
    }

    private <T extends GenericRecord> ConcurrentKafkaListenerContainerFactory<String, T> createContainerFactory(
      ConsumerFactory<String, T> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, T> containerFactory =
          new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory);
        containerFactory.setBatchListener(isBatchListener());
        containerFactory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return containerFactory;
    }

    private Map<String, Object> defaultConsumerConfig() {
        return Map.of(
          ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
          ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class,
          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers(),
          ConsumerConfig.GROUP_ID_CONFIG, groupId(),
          ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords(),
//          ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, maxBlockMs(),
          ConsumerConfig.ISOLATION_LEVEL_CONFIG, READ_COMMITTED,
          ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, isEnableAutoCommit(),
          ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, READ_FROM_EARLIEST_OFFSET,
          KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, kafkaProperties.getSchemaRegistry().getUrl(),
          KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true
        );
    }

    private String bootstrapServers() {
        return kafkaProperties.getBootstrapServers();
    }

    private String groupId() {
        return kafkaProperties.getConsumer().getGroupId();
    }

    private int maxPollRecords() {
        return kafkaProperties.getConsumer().getMaxPollRecords();
    }

    private boolean isEnableAutoCommit() {
        return kafkaProperties.getConsumer().getEnableAutoCommit();
    }

    private boolean isBatchListener() {
        return kafkaProperties.getConsumer().getBatchListener();
    }
}
