package ru.zdoher.device.collector.service;

import ru.zdoher.device.collector.domain.ReceivedDevice;

/**
 * Обработчик полученного устройства
 */
public interface ReceivedDeviceProcessor {

    /**
     * Обработать полученное устройство
     */
    void process(ReceivedDevice receivedDevice);
}
