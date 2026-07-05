package ru.zdoher.device.collector.service;

import com.proselyte.platform.events.avro.Device;

public interface EventIngestor {

    void ingest(Device deviceEvent);
}
