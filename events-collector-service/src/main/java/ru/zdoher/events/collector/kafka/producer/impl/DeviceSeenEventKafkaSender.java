package ru.zdoher.events.collector.kafka.producer.impl;

import com.proselyte.platform.events.avro.DeviceSeenEvent;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import ru.zdoher.events.collector.exception.DeviceEventKafkaSendException;
import ru.zdoher.events.collector.kafka.producer.AvroKafkaSender;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeviceSeenEventKafkaSender implements AvroKafkaSender<DeviceSeenEvent> {

    private final KafkaTemplate<String, GenericRecord> kafkaTemplate;

    @Override
    public SendResult<String, GenericRecord> send(DeviceSeenEvent message, String topic) {
        String key = message.getEventId();
        log.debug("Sending event [DeviceSeenEvent] with event ID [{}]", key);
        return Try.of(
            () -> kafkaTemplate.send(topic, key, message).get()
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
