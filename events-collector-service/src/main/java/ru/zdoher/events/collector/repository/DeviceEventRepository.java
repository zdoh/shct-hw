package ru.zdoher.events.collector.repository;

import ru.zdoher.events.collector.domain.DeviceEventReceived;

/**
 * Репозиторий для работы с событием устройста
 */
public interface DeviceEventRepository {

    /**
     * Сохранить событие
     */
    DeviceEventReceived save(DeviceEventReceived deviceEventReceived);
}
