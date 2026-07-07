package ru.zdoher.device.collector.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record Sensor(

  @Schema(requiredMode = REQUIRED)
  String sensorId,

  @Schema(requiredMode = REQUIRED)
  String sensorType,

  @Schema(requiredMode = NOT_REQUIRED)
  String unit,

  @Schema(requiredMode = NOT_REQUIRED)
  String value,

  @Schema(requiredMode = NOT_REQUIRED)
  Long measuredAtMs,

  @Schema(requiredMode = REQUIRED)
  SensorState status
) {
}
