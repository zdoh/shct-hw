package ru.zdoher.device.collector.repository;

import java.util.UUID;

public interface ProcessedDeviceEventRepository {

    boolean alreadyReceived(UUID eventId);
}
