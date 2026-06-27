package ru.zdoher.events.collector.kafka.producer;

import org.springframework.kafka.support.SendResult;

/**
 * Отправка сообщения в формате AVRO в топик Kafka
 */
public interface AvroKafkaSender<T> {

    /**
     * Отправить сообщение
     */
    SendResult<String, byte[]> send(T message, String topic);
}
