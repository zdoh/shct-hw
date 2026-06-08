package ru.zdoher.events.collector.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.support.leader.LockRegistryLeaderInitiator;
import ru.zdoher.events.collector.config.properties.EventsCollectorServiceProperties;

@Configuration
@RequiredArgsConstructor
public class LeaderElectionConfig {

    private final EventsCollectorServiceProperties eventsCollectorServiceProperties;

    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory connectionFactory) {
        return new RedisLockRegistry(
          connectionFactory,
          eventsCollectorServiceProperties.getLeaderElection()
            .getName(),
          eventsCollectorServiceProperties.getLeaderElection()
            .getExpiredAfterMs()
        );
    }

    @Bean
    public LockRegistryLeaderInitiator outboxPublisherLeaderElectionInitiator(RedisLockRegistry redisLockRegistry) {
        return new LockRegistryLeaderInitiator(redisLockRegistry);
    }
}
