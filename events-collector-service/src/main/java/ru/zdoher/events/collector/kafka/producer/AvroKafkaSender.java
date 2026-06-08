package ru.zdoher.events.collector.kafka.producer;

import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.support.SendResult;

/**
 * Отправка сообщения в формате AVRO в топик Kafka
 */
public interface AvroKafkaSender<T extends GenericRecord> {

    /**
     * Отправить сообщение
     */
    SendResult<String, GenericRecord> send(T message, String topic);
}
