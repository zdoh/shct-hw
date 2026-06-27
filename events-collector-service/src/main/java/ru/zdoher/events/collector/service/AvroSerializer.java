package ru.zdoher.events.collector.service;

import org.apache.avro.specific.SpecificRecord;

public interface AvroSerializer {

    byte[] serializeWithRegistry(SpecificRecord record, String topic);
}
