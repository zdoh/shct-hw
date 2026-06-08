package ru.zdoher.events.collector.service;

import java.time.OffsetDateTime;

public interface DeviceOutboxProcessor {
    
    Long process(OffsetDateTime processAt);
}
