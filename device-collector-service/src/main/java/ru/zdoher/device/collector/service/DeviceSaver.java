package ru.zdoher.device.collector.service;

import ru.zdoher.device.collector.domain.Device;

public interface DeviceSaver {

    Device save(Device receivedDevice);
}
