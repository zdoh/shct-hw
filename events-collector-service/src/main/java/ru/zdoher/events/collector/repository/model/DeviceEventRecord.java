package ru.zdoher.events.collector.repository.model;

import ru.zdoher.events.collector.domain.DeviceEventType;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record DeviceEventRecord(
  String deviceId,
  String eventId,
  DeviceEventType eventType,
  Long eventTimeMs,
  String sourceSystem,
  String payload,
  LocalDate eventDate,
  OffsetDateTime ingestedAt,
  DeviceRecord device
) {
}
