package ru.zdoher.events.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zdoher.events.collector.config.properties.KafkaProperties;
import ru.zdoher.events.collector.domain.DeviceEventOutbox;
import ru.zdoher.events.collector.kafka.producer.AvroKafkaSender;
import ru.zdoher.events.collector.service.DeviceSeenEventSender;

@Service
@RequiredArgsConstructor
public class DeviceSeenEventSendingService implements DeviceSeenEventSender {

    private final AvroKafkaSender<DeviceEventOutbox> avroKafkaSender;
    private final KafkaProperties kafkaProperties;

    @Override
    public DeviceEventOutbox send(DeviceEventOutbox deviceEventOutbox) {
        avroKafkaSender.send(
          deviceEventOutbox,
          kafkaProperties.getProducer()
            .getTopics()
            .getSendDeviceEvent()
        );

        return deviceEventOutbox;
    }
}
