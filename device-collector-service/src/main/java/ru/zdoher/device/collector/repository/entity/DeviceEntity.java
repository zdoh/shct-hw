package ru.zdoher.device.collector.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(schema = "device_collector_service", name = "device")
public class DeviceEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "external_device_id", nullable = false)
    private String externalDeviceId;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "device_type", nullable = false)
    private String deviceType;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    @Column(name = "firmware_version")
    private String firmwareVersion;

    @Column(name = "registered_at_ms")
    private Long registeredAtMs;

//    @JdbcTypeCode(SqlTypes.JSON)
//    @Column(name = "settings", nullable = false, columnDefinition = "jsonb")
//    private Map<String, String> settings = new HashMap<>();
//
//    @JdbcTypeCode(SqlTypes.JSON)
//    @Column(name = "attributes", nullable = false, columnDefinition = "jsonb")
//    private Map<String, String> attributes = new HashMap<>();
}
