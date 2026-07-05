package ru.zdoher.device.collector.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(schema = "device_collector_service", name = "sensor")
public class SensorEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "device_id", nullable = false)
    private UUID deviceId;

    @Column(name = "external_device_id", nullable = false)
    String externalDeviceId;

    @Column(name = "sensor_id", nullable = false)
    private String sensorId;

    @Column(name = "sensor_type", nullable = false)
    private String sensorType;

    @Column(name = "unit")
    private String unit;

    @Column(name = "value")
    private String value;

    @Column(name = "measured_at_ms")
    private Long measuredAtMs;

    @Column(name = "state", nullable = false)
    private String state;
}
