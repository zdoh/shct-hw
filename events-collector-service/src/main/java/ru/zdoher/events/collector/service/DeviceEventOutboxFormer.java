package ru.zdoher.events.collector.service;

import ru.zdoher.events.collector.domain.DeviceEventOutbox;
import ru.zdoher.events.collector.domain.DeviceEventReceived;

import java.time.OffsetDateTime;

/**
 * Сформировать outbox для DeviceEvent
 */
public interface DeviceEventOutboxFormer {

    DeviceEventOutbox form(DeviceEventReceived deviceEventReceived, OffsetDateTime createdAt);
}
