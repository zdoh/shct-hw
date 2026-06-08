package ru.zdoher.events.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zdoher.events.collector.domain.DeviceEventOutbox;
import ru.zdoher.events.collector.domain.DeviceEventOutboxStatus;
import ru.zdoher.events.collector.repository.DeviceEventOutboxRepository;
import ru.zdoher.events.collector.service.DeviceEventOutboxStatusUpdater;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class DeviceEventOutboxStatusUpdatingService implements DeviceEventOutboxStatusUpdater {

    private final DeviceEventOutboxRepository deviceEventOutboxRepository;

    @Override
    public DeviceEventOutbox sentSuccessfully(DeviceEventOutbox deviceEventOutbox, OffsetDateTime processAt) {
        return deviceEventOutboxRepository.save(
          deviceEventOutbox.toBuilder()
            .updatedAt(processAt)
            .status(DeviceEventOutboxStatus.SENT)
            .attempts(deviceEventOutbox.attempts() + 1)
            .build()
        );
    }

    @Override
    public DeviceEventOutbox sendingError(
      DeviceEventOutbox deviceEventOutbox,
      OffsetDateTime processAt,
      String errorMessage
    ) {
        return deviceEventOutboxRepository.save(
          deviceEventOutbox.toBuilder()
            .updatedAt(processAt)
            .attempts(deviceEventOutbox.attempts() + 1)
            .status(DeviceEventOutboxStatus.FAILED)
            .lastError(errorMessage)
            .build()
        );
    }
}
