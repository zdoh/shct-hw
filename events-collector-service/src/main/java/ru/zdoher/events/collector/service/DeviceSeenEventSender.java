package ru.zdoher.events.collector.service;

import ru.zdoher.events.collector.domain.DeviceEventOutbox;

/**
 * Отправка события об обнаружении устройства
 */
public interface DeviceSeenEventSender {

    /**
     * Отправить событие
     */
    DeviceEventOutbox send(DeviceEventOutbox deviceEventOutbox);
}
