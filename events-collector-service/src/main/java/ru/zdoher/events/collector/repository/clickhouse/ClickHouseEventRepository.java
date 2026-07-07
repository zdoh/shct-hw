package ru.zdoher.events.collector.repository.clickhouse;

import ru.zdoher.events.collector.repository.model.DeviceEventRecord;

/**
 * Репозиторий БД clickhouse для работы с событием
 */
public interface ClickHouseEventRepository {

    /**
     * Сохранить событие
     */
    DeviceEventRecord save(DeviceEventRecord deviceEventRecord);
}
