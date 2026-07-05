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

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(schema = "device_collector_service", name = "processed_device_event")
public class ProcessedDeviceEventEntity {

    @Id
    @Column(name = "eventId", nullable = false)
    private UUID eventId;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime created_at;
}
