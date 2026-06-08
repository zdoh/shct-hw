package ru.zdoher.events.collector.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.zdoher.events.collector.domain.DeviceEventOutbox;
import ru.zdoher.events.collector.repository.DeviceEventOutboxRepository;
import ru.zdoher.events.collector.service.DeviceEventOutboxStatusUpdater;
import ru.zdoher.events.collector.service.DeviceOutboxProcessor;
import ru.zdoher.events.collector.service.DeviceSeenEventSender;

import java.time.OffsetDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceOutboxProcessingService implements DeviceOutboxProcessor {

    private final DeviceEventOutboxRepository deviceEventOutboxRepository;
    private final DeviceSeenEventSender deviceSeenEventSender;
    private final DeviceEventOutboxStatusUpdater deviceEventOutboxStatusUpdater;

    @Override
    public Long process(OffsetDateTime processAt) {
        return deviceEventOutboxRepository.getUnsent()
          .stream()
          .map(deviceOutbox -> send(deviceOutbox, processAt))
          .flatMap(Optional::stream)
          .count();
    }

    private Optional<DeviceEventOutbox> send(DeviceEventOutbox deviceEventOutbox, OffsetDateTime processAt) {
        try {
            return Optional.of(
              deviceEventOutboxStatusUpdater.sentSuccessfully(
                deviceSeenEventSender.send(deviceEventOutbox),
                processAt
              )
            );
        } catch (Exception e) {
            log.error("Error while send DeviceEventOutbox {}", e.getMessage(), e);
            deviceEventOutboxStatusUpdater.sendingError(
              deviceEventOutbox,
              processAt,
              e.getMessage()
            );
            return Optional.empty();
        }
    }
}


