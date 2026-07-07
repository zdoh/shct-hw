package ru.zdoher.device.collector.repository;

import ru.zdoher.device.collector.domain.ExtendedSensor;

import java.util.List;

public interface SensorRepository {

    ExtendedSensor save(ExtendedSensor extendedSensor);

    List<ExtendedSensor> saveAll(List<ExtendedSensor> extendedSensors);
}
