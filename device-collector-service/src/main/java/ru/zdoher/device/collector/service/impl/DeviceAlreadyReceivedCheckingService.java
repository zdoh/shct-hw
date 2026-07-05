package ru.zdoher.device.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zdoher.device.collector.repository.ProcessedDeviceEventRepository;
import ru.zdoher.device.collector.service.DeviceAlreadyReceivedChecker;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceAlreadyReceivedCheckingService implements DeviceAlreadyReceivedChecker {

    private final ProcessedDeviceEventRepository processedDeviceEventRepository;

    @Override
    public boolean alreadyReceived(UUID eventId) {
        return processedDeviceEventRepository.alreadyReceived(eventId);
    }
}
