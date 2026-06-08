package ru.zdoher.events.collector.config.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "events-collector-service")
public class EventsCollectorServiceProperties {

    @Valid
    @NestedConfigurationProperty
    private LeaderElectionProperties leaderElection;

    @Data
    public static class LeaderElectionProperties {

        @NotBlank
        private String name;

        @Min(1000)
        private Integer expiredAfterMs;
    }
}
