package ru.zdoher.events.collector.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.integration.leader.event.AbstractLeaderEvent;
import org.springframework.integration.leader.event.OnGrantedEvent;
import org.springframework.integration.leader.event.OnRevokedEvent;
import org.springframework.stereotype.Component;
import ru.zdoher.events.collector.config.properties.EventsCollectorServiceProperties;

@Slf4j
@Component
@RequiredArgsConstructor
public class LeaderEventListener implements ApplicationListener<AbstractLeaderEvent> {

    private final EventsCollectorServiceProperties eventsCollectorServiceProperties;

    @Override
    public void onApplicationEvent(AbstractLeaderEvent event) {
        if (event instanceof OnGrantedEvent grantedEvent) {
            if (
              eventsCollectorServiceProperties.getLeaderElection()
                .getName()
                .equals(grantedEvent.getRole())
            ) {
                log.info("Take a leader election lock");
            }
        } else if (event instanceof OnRevokedEvent revokedEvent) {
            if (
              eventsCollectorServiceProperties.getLeaderElection()
                .getName()
                .equals(revokedEvent.getRole())) {
                log.info("Release a leader election lock");
            }
        }
    }
}
