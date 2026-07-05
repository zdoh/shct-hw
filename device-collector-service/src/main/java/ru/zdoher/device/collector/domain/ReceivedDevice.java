package ru.zdoher.device.collector.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Производное событие для создания или обновления устройства в device-collector-service.")
public record ReceivedDevice(

  @Schema(description = "Уникальный идентификатор производного события.", requiredMode = REQUIRED)
  UUID eventId,

  @Schema(description = "Идентификатор исходного события из topic events.", requiredMode = REQUIRED)
  String sourceEventId,

  @Schema(description = "Тип изменения, которое нужно применить к модели устройства.", requiredMode = REQUIRED)
  EventType eventType,

  @Schema(description = "Время публикации производного события в миллисекундах Unix-времени.", requiredMode = REQUIRED)
  Long emittedAtMs,

  @Schema(description = "Снимок текущего состояния устройства.", requiredMode = REQUIRED)
  Device device
) {

}
