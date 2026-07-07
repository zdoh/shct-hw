package ru.zdoher.events.collector.kafka.producer.impl;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import ru.zdoher.events.collector.domain.DeviceEventOutbox;
import ru.zdoher.events.collector.exception.DeviceEventKafkaSendException;
import ru.zdoher.events.collector.kafka.producer.AvroKafkaSender;

import java.util.Base64;
import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeviceSeenEventKafkaSender implements AvroKafkaSender<DeviceEventOutbox> {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @Override
    public SendResult<String, byte[]> send(DeviceEventOutbox message, String topic) {
        String key = message.eventId();
        log.debug("Sending event [DeviceSeenEvent] with event ID [{}]", key);
        return Try.of(
            () -> kafkaTemplate.send(
              topic,
              key,
              Base64.getDecoder().decode(message.payload())
            ).get()
          )
          .onFailure(interruptCurrentThread())
          .get();
    }

    private Consumer<Throwable> interruptCurrentThread() {
        return (Throwable cause) -> {
            if (cause instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new DeviceEventKafkaSendException(cause);
        };
    }
}
