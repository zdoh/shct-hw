package ru.zdoher.device.collector.service.impl;

import com.proselyte.platform.events.avro.Device;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zdoher.device.collector.kafka.mapper.DeviceReceivedMapper;
import ru.zdoher.device.collector.service.DeviceAlreadyReceivedChecker;
import ru.zdoher.device.collector.service.EventIngestor;
import ru.zdoher.device.collector.service.ReceivedDeviceProcessor;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventIngestionService implements EventIngestor {

    private final DeviceReceivedMapper deviceReceivedMapper;
    private final ReceivedDeviceProcessor receivedDeviceProcessor;
    private final DeviceAlreadyReceivedChecker deviceAlreadyReceivedChecker;

    @Override
    @Transactional
    public void ingest(Device device) {
        if (!deviceAlreadyReceivedChecker.alreadyReceived(UUID.fromString(device.getEventId()))) {
            receivedDeviceProcessor.process(
              deviceReceivedMapper.from(
                device,
                OffsetDateTime.now(ZoneId.systemDefault())
              )
            );
        } else {
            log.warn(
              "Device [{}] with eventId [{}] already processed. Ignored",
              device.getDevice()
                .getDeviceId(),
              device.getEventId())
            ;
        }
    }
}
