package ru.zdoher.events.collector.repository;

import ru.zdoher.events.collector.domain.DeviceEventOutbox;

import java.util.List;

/**
 * Репозиторий для работы outbox
 */
public interface DeviceEventOutboxRepository {

    /**
     * Сохранить outbox
     */
    DeviceEventOutbox save(DeviceEventOutbox deviceEventOutbox);

    /**
     * Получить не отправленные Outbox
     */
    List<DeviceEventOutbox> getUnsent();
}
