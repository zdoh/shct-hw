package ru.zdoher.events.collector.repository.clickhouse;

import ru.zdoher.events.collector.repository.model.DeviceEvent;

/**
 * Репозиторий БД clickhouse для работы с событием
 */
public interface ClickHouseEventRepository {

    /**
     * Сохранить событие
     */
    DeviceEvent save(DeviceEvent deviceEvent);
}
