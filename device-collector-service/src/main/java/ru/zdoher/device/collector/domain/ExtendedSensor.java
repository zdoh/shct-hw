package ru.zdoher.device.collector.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
@Schema(description = "Расширенный сенсор. Сенсор с дополнительными полями")
public record ExtendedSensor(

  @Schema(description = "Внутренний идентификатор сенсора", requiredMode = REQUIRED)
  UUID id,

  @Schema(description = "Внутренний идентификатор связанного устройства", requiredMode = REQUIRED)
  UUID deviceId,

  @Schema(description = "Внешний идентификатор связанного устройства", requiredMode = REQUIRED)
  String externalDeviceId,

  @Schema(description = "Внутренний идентификатор устрайства", requiredMode = REQUIRED)
  Sensor sensor
) {
}
