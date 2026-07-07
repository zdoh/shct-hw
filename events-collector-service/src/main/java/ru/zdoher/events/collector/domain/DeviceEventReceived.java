package ru.zdoher.events.collector.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record DeviceEventReceived(
  String eventId,
  DeviceEventType eventType,
  Long eventTimeMs,
  String sourceSystem,
  String payload,
  LocalDate eventDate,
  OffsetDateTime ingestedAt,
  Device device
) {
}
