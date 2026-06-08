package ru.zdoher.events.collector.service;

import ru.zdoher.events.collector.domain.DeviceEventReceived;

/**
 * Обработчик события устройста
 */
public interface DeviceEventProcessor {

    /**
     * Обработать событие
     */
    void process(DeviceEventReceived deviceEvent);
}
