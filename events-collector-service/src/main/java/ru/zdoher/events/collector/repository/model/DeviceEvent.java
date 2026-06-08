package ru.zdoher.events.collector.repository.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record DeviceEvent(
  String eventId,
  String deviceId,
  Long timestampMs,
  String eventType,
  String payload,
  LocalDate eventDate,
  OffsetDateTime ingestedAt
) {
}
