package ru.zdoher.events.collector.kafka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.zdoher.events.collector.domain.Device;
import ru.zdoher.events.collector.domain.DeviceEventReceived;
import ru.zdoher.events.collector.domain.Sensor;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DeviceMapper {

    @Mapping(target = "eventId", source = "eventId")
    @Mapping(target = "sourceEventId", source = "event.eventId")
    @Mapping(target = "eventType", source = "event.eventType")
    @Mapping(target = "emittedAtMs", source = "event.eventTimeMs")
    @Mapping(target = "device", source = "event.device")
    com.proselyte.platform.events.avro.Device from(DeviceEventReceived event, String eventId);

    com.proselyte.platform.events.avro.DeviceSnapshot  from(Device device);

    com.proselyte.platform.events.avro.SensorSnapshot from(Sensor sensor);
}
