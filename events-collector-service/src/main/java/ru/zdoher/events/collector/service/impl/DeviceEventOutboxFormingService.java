package ru.zdoher.events.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zdoher.events.collector.config.properties.KafkaProperties;
import ru.zdoher.events.collector.domain.DeviceEventOutbox;
import ru.zdoher.events.collector.domain.DeviceEventOutboxStatus;
import ru.zdoher.events.collector.domain.DeviceEventReceived;
import ru.zdoher.events.collector.kafka.mapper.DeviceMapper;
import ru.zdoher.events.collector.service.AvroSerializer;
import ru.zdoher.events.collector.service.DeviceEventOutboxFormer;

import java.time.OffsetDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class DeviceEventOutboxFormingService implements DeviceEventOutboxFormer {

    private final static Integer INITIATED_ATTEMPT_VALUE = 0;
    private final static Integer INITIATED_STATE_VERSION = 0;

    private final KafkaProperties kafkaProperties;
    private final AvroSerializer avroSerializer;
    private final DeviceMapper deviceMapper;

    @Override
    public DeviceEventOutbox form(
      DeviceEventReceived deviceEventReceived,
      OffsetDateTime createdAt,
      String eventId
    ) {
        return DeviceEventOutbox.builder()
          .eventId(eventId)
          .sourceEventId(deviceEventReceived.eventId())
          .sourceTimestampMs(deviceEventReceived.eventTimeMs())
          .createdAt(createdAt)
          .updatedAt(createdAt)
          .stateVersion(INITIATED_STATE_VERSION)
          .status(DeviceEventOutboxStatus.NEW)
          .attempts(INITIATED_ATTEMPT_VALUE)
          .payload(
            Base64.getEncoder()
              .encodeToString(
                avroSerializer.serializeWithRegistry(
                  deviceMapper.from(deviceEventReceived, eventId),
                  kafkaProperties.getProducer().getTopics().getSendDeviceEvent()
                )
              )
          )
          .build();
    }
}
