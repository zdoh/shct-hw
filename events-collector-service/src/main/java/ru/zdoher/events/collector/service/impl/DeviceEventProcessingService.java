package ru.zdoher.events.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zdoher.events.collector.domain.DeviceEventReceived;
import ru.zdoher.events.collector.repository.DeviceEventRepository;
import ru.zdoher.events.collector.service.DeviceEventOutboxCreator;
import ru.zdoher.events.collector.service.DeviceEventProcessor;

@Service
@RequiredArgsConstructor
public class DeviceEventProcessingService implements DeviceEventProcessor {

    private final DeviceEventRepository deviceEventRepository;
    private final DeviceEventOutboxCreator deviceEventOutboxCreator;

    @Override
    public void process(DeviceEventReceived deviceEvent) {
        deviceEventOutboxCreator.create(
          deviceEventRepository.save(deviceEvent)
        );
    }
}
