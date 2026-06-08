package ru.zdoher.events.collector.service;

import ru.zdoher.events.collector.domain.DeviceEventOutbox;

import java.time.OffsetDateTime;

/**
 * Изменение статуса Outbox для DeviceEvent
 */
public interface DeviceEventOutboxStatusUpdater {

    /**
     * Изменить статус Outbox для DeviceEvent в случае успешной отправки
     */
    DeviceEventOutbox sentSuccessfully(DeviceEventOutbox deviceEventOutbox, OffsetDateTime processAt);

    /**
     * Ошибка при отправки
     */
    DeviceEventOutbox sendingError(DeviceEventOutbox deviceEventOutbox, OffsetDateTime processAt, String errorMessage);
}
