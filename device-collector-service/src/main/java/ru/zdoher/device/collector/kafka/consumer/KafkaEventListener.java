package ru.zdoher.device.collector.kafka.consumer;

import com.proselyte.platform.events.avro.Device;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import ru.zdoher.device.collector.service.EventIngestor;

import static ru.zdoher.device.collector.config.KafkaConsumerConfig.DEVICE_LISTENER_CONTAINER_FACTORY;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventListener {

    private final EventIngestor eventIngestor;

    @RetryableTopic(
      attempts = "${spring.kafka.consumer.topics.collect-device.retry.attempts}",
      backOff = @BackOff(
        delayString =  "${spring.kafka.consumer.topics.collect-device.retry.backoff-delay-ms}",
        multiplierString = "${spring.kafka.consumer.topics.collect-device.retry.backoff-multiply}"
      )
    )
    @KafkaListener(
      topics = "${spring.kafka.consumer.topics.collect-device.topic-name}",
      containerFactory = DEVICE_LISTENER_CONTAINER_FACTORY
    )
    public void listen(Device device, Acknowledgment ack) {
        try {
            log.debug(
              "Received event [Device] with event ID [{}]",
              device.getEventId()
            );

            eventIngestor.ingest(device);
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Got an error {}", e.getMessage(), e);
            throw new RuntimeException("Retry later", e);
        }
    }
}
