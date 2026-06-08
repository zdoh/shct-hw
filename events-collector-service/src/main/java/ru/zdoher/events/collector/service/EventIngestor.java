package ru.zdoher.events.collector.service;

import com.proselyte.platform.events.avro.DeviceEvent;

public interface EventIngestor {

    void ingest(DeviceEvent deviceEvent);
}
