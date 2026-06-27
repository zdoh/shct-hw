package ru.zdoher.events.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zdoher.events.collector.domain.DeviceEventOutbox;
import ru.zdoher.events.collector.domain.DeviceEventReceived;
import ru.zdoher.events.collector.repository.DeviceEventOutboxRepository;
import ru.zdoher.events.collector.service.DeviceEventOutboxCreator;
import ru.zdoher.events.collector.service.DeviceEventOutboxFormer;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceEventOutboxCreationService implements DeviceEventOutboxCreator {

    private final DeviceEventOutboxFormer deviceEventOutboxFormer;
    private final DeviceEventOutboxRepository deviceEventOutboxRepository;

    @Override
    public DeviceEventOutbox create(DeviceEventReceived deviceEventReceived) {
        return deviceEventOutboxRepository.save(
          deviceEventOutboxFormer.form(
            deviceEventReceived,
            OffsetDateTime.now(),
            UUID.randomUUID().toString()
          )
        );
    }
}
