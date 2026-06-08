package ru.zdoher.events.collector.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import static ru.zdoher.events.collector.config.KafkaConsumerConfig.DEVICE_EVENT_LISTENER_CONTAINER_FACTORY;

import com.proselyte.platform.events.avro.DeviceEvent;
import ru.zdoher.events.collector.service.EventIngestor;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventListener {

    private final EventIngestor eventIngestor;

    @KafkaListener(
      topics = "${spring.kafka.consumer.topics.collect-device-event}",
      containerFactory = DEVICE_EVENT_LISTENER_CONTAINER_FACTORY

    )
    public void listen(DeviceEvent deviceEvent, Acknowledgment ack) {
        try {
            log.debug(
              "Received event [DeviceEvent] with event ID {} and device ID [{}]",
              deviceEvent.getEventId(),
              deviceEvent.getDeviceId()
            );

            eventIngestor.ingest(deviceEvent);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Got an error {}", e.getMessage(), e);
        }
    }
}
