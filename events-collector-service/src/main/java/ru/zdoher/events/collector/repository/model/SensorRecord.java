package ru.zdoher.events.collector.repository.model;

import ru.zdoher.events.collector.domain.SensorState;

public record SensorRecord(
  String sensorId,
  String sensorType,
  String unit,
  String value,
  Long measuredAtMs,
  SensorState status
) {
}
