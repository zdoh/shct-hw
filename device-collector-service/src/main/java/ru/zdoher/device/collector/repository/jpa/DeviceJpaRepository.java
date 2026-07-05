package ru.zdoher.device.collector.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zdoher.device.collector.repository.entity.DeviceEntity;

import java.util.UUID;

public interface DeviceJpaRepository extends JpaRepository<DeviceEntity, UUID> {
}
