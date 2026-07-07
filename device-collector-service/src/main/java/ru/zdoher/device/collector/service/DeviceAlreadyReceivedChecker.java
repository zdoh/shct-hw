package ru.zdoher.device.collector.service;

import java.util.UUID;

public interface DeviceAlreadyReceivedChecker {

    boolean alreadyReceived(UUID eventId);
}
