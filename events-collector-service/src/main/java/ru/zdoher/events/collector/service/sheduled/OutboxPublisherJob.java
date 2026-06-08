package ru.zdoher.events.collector.service.sheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.integration.support.leader.LockRegistryLeaderInitiator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.zdoher.events.collector.service.DeviceOutboxProcessor;

import java.time.OffsetDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxPublisherJob {

    private final LockRegistryLeaderInitiator outboxPublisherLeaderElectionInitiator;
    private final DeviceOutboxProcessor deviceOutboxProcessor;


    @Scheduled(fixedDelayString = "${events-collector-service.outbox.schedule.device-publisher-delay}")
    void runJob() {
        if(outboxPublisherLeaderElectionInitiator.getContext().isLeader()) {
            val sentCount = deviceOutboxProcessor.process(OffsetDateTime.now());
            log.info("Send {} outbox messages", sentCount);
        }
    }
}
