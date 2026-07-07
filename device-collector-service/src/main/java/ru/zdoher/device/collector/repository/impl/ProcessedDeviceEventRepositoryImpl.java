package ru.zdoher.device.collector.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.zdoher.device.collector.repository.ProcessedDeviceEventRepository;
import ru.zdoher.device.collector.repository.jpa.ProcessedDeviceEventJpaRepository;

import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProcessedDeviceEventRepositoryImpl implements ProcessedDeviceEventRepository {

    private final ProcessedDeviceEventJpaRepository processedDeviceEventJpaRepository;

    @Override
    public boolean alreadyReceived(UUID eventId) {
        return processedDeviceEventJpaRepository.insertIgnoringDuplicate(eventId, OffsetDateTime.now()) == 0;
    }
}
