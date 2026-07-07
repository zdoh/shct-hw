package ru.zdoher.device.collector.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
@Schema(description = "Расширенное устройство. Устройство с дополнительными полями")
public record ExtendedDevice(

  @Schema(description = "Внутренний идентификатор устрайства", requiredMode = REQUIRED)
  UUID id,

  @Schema(description = "Дата создания записи", requiredMode = REQUIRED)
  OffsetDateTime createdAt,

  @Schema(description = "Устройство", requiredMode = REQUIRED)
  Device device
) {
}
