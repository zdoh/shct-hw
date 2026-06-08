package ru.zdoher.events.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zdoher.events.collector.service.DeviceEventProcessor;
import ru.zdoher.events.collector.service.EventIngestor;
import ru.zdoher.events.collector.kafka.mapper.DeviceEventReceivedMapper;

import com.proselyte.platform.events.avro.DeviceEvent;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class EventIngestionService implements EventIngestor {

    private final DeviceEventReceivedMapper deviceEventReceivedMapper;
    private final DeviceEventProcessor deviceEventProcessor;

    @Override
    public void ingest(DeviceEvent deviceEvent) {
        deviceEventProcessor.process(
          deviceEventReceivedMapper.from(
            deviceEvent,
            LocalDate.now(),
            OffsetDateTime.now(ZoneId.systemDefault())
          )
        );
    }
}
