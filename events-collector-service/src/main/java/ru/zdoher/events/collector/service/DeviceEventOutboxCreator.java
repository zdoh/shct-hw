package ru.zdoher.events.collector.service;

import ru.zdoher.events.collector.domain.DeviceEventOutbox;
import ru.zdoher.events.collector.domain.DeviceEventReceived;

/**
 * Создание outbox для DeviceEvent
 */
public interface DeviceEventOutboxCreator {

    /**
     * Создать outbox для DeviceEvent
     */
    DeviceEventOutbox create(DeviceEventReceived deviceEventReceived);
}
