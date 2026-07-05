package ru.zdoher.device.collector.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Schema(description = "Снимок текущего состояния устройства")
public record Device(

  @Schema(requiredMode = REQUIRED)
  String externalDeviceId,

  @Schema(requiredMode = REQUIRED)
  String tenantId,

  @Schema(requiredMode = NOT_REQUIRED)
  String ownerId,

  @Schema(requiredMode = REQUIRED)
  String deviceType,

  @Schema(requiredMode = NOT_REQUIRED)
  String serialNumber,

  @Schema(requiredMode = NOT_REQUIRED)
  String manufacturer,

  @Schema(requiredMode = NOT_REQUIRED)
  String model,

  @Schema(requiredMode = NOT_REQUIRED)
  String firmwareVersion,

  @Schema(requiredMode = REQUIRED)
  DeviceState status,

  @Schema(requiredMode = NOT_REQUIRED)
  Long registeredAtMs,

  @Schema(requiredMode = NOT_REQUIRED)
  Long lastSeenAtMs,

  @Schema(requiredMode = REQUIRED)
  Map<String, String> settings,

  @Schema(requiredMode = REQUIRED)
  Map<String, String> attributes,

  @Schema(requiredMode = REQUIRED)
  List<Sensor> sensors
) {
}
