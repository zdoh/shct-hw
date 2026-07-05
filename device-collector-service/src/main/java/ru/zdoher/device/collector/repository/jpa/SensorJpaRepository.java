package ru.zdoher.device.collector.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zdoher.device.collector.repository.entity.SensorEntity;

import java.util.UUID;

public interface SensorJpaRepository extends JpaRepository<SensorEntity, UUID> {
}
