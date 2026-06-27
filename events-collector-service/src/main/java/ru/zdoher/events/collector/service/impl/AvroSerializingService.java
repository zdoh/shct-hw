package ru.zdoher.events.collector.service.impl;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.stereotype.Service;
import ru.zdoher.events.collector.service.AvroSerializer;


@Service
@RequiredArgsConstructor
public class AvroSerializingService implements AvroSerializer {

    private final KafkaAvroSerializer serializer;

    @Override
    public byte[] serializeWithRegistry(SpecificRecord record, String topic) {

        return serializer.serialize(topic, record);
    }
}
