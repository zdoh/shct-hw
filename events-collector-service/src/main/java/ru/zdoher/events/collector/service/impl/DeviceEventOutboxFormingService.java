package ru.zdoher.events.collector.service.impl;

import org.springframework.stereotype.Service;
import ru.zdoher.events.collector.domain.DeviceEventOutbox;
import ru.zdoher.events.collector.domain.DeviceEventOutboxStatus;
import ru.zdoher.events.collector.domain.DeviceEventReceived;
import ru.zdoher.events.collector.service.DeviceEventOutboxFormer;

import java.time.OffsetDateTime;

@Service
public class DeviceEventOutboxFormingService implements DeviceEventOutboxFormer {

    private final static Integer INITIATED_ATTEMPT_VALUE = 0;
    private final static Integer INITIATED_STATE_VERSION = 0;

    @Override
    public DeviceEventOutbox form(DeviceEventReceived deviceEventReceived, OffsetDateTime createdAt) {
        return DeviceEventOutbox.builder()
          .eventId(deviceEventReceived.eventId())
          .deviceId(deviceEventReceived.deviceId())
          .sourceTimestampMs(deviceEventReceived.timestampMs())
          .createdAt(createdAt)
          .updatedAt(createdAt)
          .stateVersion(INITIATED_STATE_VERSION)
          .status(DeviceEventOutboxStatus.NEW)
          .attempts(INITIATED_ATTEMPT_VALUE)
          .build();
    }
}
