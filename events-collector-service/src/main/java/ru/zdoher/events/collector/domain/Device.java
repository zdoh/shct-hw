package ru.zdoher.events.collector.domain;

import java.util.List;
import java.util.Map;

public record Device(
  String deviceId,
  String tenantId,
  String ownerId,
  String deviceType,
  String serialNumber,
  String manufacturer,
  String model,
  String firmwareVersion,
  DeviceState status,
  Long registeredAtMs,
  Long lastSeenAtMs,
  Map<String, String> settings,
  Map<String, String> attributes,
  List<Sensor> sensors
) {

}
