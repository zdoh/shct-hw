package ru.zdoher.device.collector.kafka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.zdoher.device.collector.domain.Device;
import ru.zdoher.device.collector.domain.ReceivedDevice;
import ru.zdoher.device.collector.domain.Sensor;

import java.time.OffsetDateTime;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DeviceReceivedMapper {

    @Mapping(target = "eventId", source = "device.eventId")
    @Mapping(target = "sourceEventId", source = "device.sourceEventId")
    @Mapping(target = "eventType", source = "device.eventType")
    @Mapping(target = "emittedAtMs", source = "device.emittedAtMs")
    @Mapping(target = "device", source = "device.device")
    ReceivedDevice from(com.proselyte.platform.events.avro.Device device, OffsetDateTime receivedAt);

    @Mapping(target = "externalDeviceId", source = "deviceId")
    Device from(com.proselyte.platform.events.avro.DeviceSnapshot device);

    Sensor from(com.proselyte.platform.events.avro.SensorSnapshot sensor);
}
