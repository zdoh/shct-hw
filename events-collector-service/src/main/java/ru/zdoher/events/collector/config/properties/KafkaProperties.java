package ru.zdoher.events.collector.config.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaProperties {

    @NotBlank
    private String bootstrapServers;

    @Valid
    @NestedConfigurationProperty
    private SchemaRegistryProperties schemaRegistry;

    @Valid
    @NestedConfigurationProperty
    private ConsumerProperties consumer;

    @Valid
    @NestedConfigurationProperty
    private ProducerProperties producer;


    @Data
    public static class SchemaRegistryProperties {

        @NotBlank
        private String url;
    }

    @Data
    public static class ConsumerProperties {

        @NotBlank
        private String groupId;

        @Min(1)
        private Integer maxPollRecords;

        private Boolean enableAutoCommit;

        @NotBlank
        private String manual;

        private Boolean batchListener;

        @Valid
        @NestedConfigurationProperty
        private ConsumerTopicProperties topics;


        @Data
        public static class ConsumerTopicProperties {

            @NotBlank
            private String collectDeviceEvent;
        }
    }

    @Data
    public static class ProducerProperties {

        @Valid
        @NestedConfigurationProperty
        private ProducerTopicProperties topics;

        @Min(0)
        private Integer maxRetries;

        @Max(5)
        private Integer maxInFlightRequestsPerConnection;

        @Min(0)
        private Long maxBlockMs;

        @Data
        public static class ProducerTopicProperties {

            @NotBlank
            private String sendDeviceEvent;

        }
    }

}
