package ru.zdoher.events.collector.repository.clickhouse;

import ru.zdoher.events.collector.repository.model.DeviceOutbox;

import java.util.List;

/**
 * Репозиторий БД clickhouse для работы outbox
 */
public interface ClickHouseOutboxRepository {

    DeviceOutbox save(DeviceOutbox deviceOutbox);

    List<DeviceOutbox> getUnsent();
}
