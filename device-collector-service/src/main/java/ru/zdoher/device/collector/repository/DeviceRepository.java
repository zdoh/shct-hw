package ru.zdoher.device.collector.repository;

import ru.zdoher.device.collector.domain.ExtendedDevice;

public interface DeviceRepository {

    ExtendedDevice save(ExtendedDevice extendedDevice);
}
