package ru.zdoher.events.collector.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record DeviceEventReceived(
  String eventId,
  String deviceId,
  Long timestampMs,
  String eventType,
  String payload,
  LocalDate eventDate,
  OffsetDateTime ingestedAt
) {
}
