package ru.zdoher.events.collector.domain;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder(toBuilder = true)
public record DeviceEventOutbox(
  String eventId,
  String sourceEventId,
  Long sourceTimestampMs,
  OffsetDateTime createdAt,
  OffsetDateTime updatedAt,
  Integer stateVersion,
  DeviceEventOutboxStatus status,
  Integer attempts,
  String lastError,
  String payload
) {
}
