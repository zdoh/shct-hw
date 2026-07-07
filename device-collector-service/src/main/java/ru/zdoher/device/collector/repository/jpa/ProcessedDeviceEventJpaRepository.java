package ru.zdoher.device.collector.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.zdoher.device.collector.repository.entity.ProcessedDeviceEventEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface ProcessedDeviceEventJpaRepository extends JpaRepository<ProcessedDeviceEventEntity, UUID> {

    @Modifying
    @Query(value = "INSERT INTO device_collector_service.processed_device_event (event_id, created_at)" +
      " VALUES (:eventId, :createdAt) ON CONFLICT (event_id) DO NOTHING", nativeQuery = true)
    int insertIgnoringDuplicate(UUID eventId, OffsetDateTime createdAt);
}
