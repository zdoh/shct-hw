package ru.zdoher.events.collector.domain;

public record Sensor(
  String sensorId,
  String sensorType,
  String unit,
  String value,
  Long measuredAtMs,
  SensorState status
  ) {
}