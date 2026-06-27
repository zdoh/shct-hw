package ru.zdoher.events.collector.repository.model;

import lombok.Builder;
import ru.zdoher.events.collector.domain.DeviceEventOutboxStatus;

import java.time.OffsetDateTime;

@Builder
public record DeviceOutbox(
  String eventId,
  String sourceEventId,
  Long sourceTimestampMs,
  OffsetDateTime createdAt,
  OffsetDateTime updatedAt,
  Long stateVersion,
  DeviceEventOutboxStatus status,
  Integer attempts,
  String lastError,
  String payload
) {
}
