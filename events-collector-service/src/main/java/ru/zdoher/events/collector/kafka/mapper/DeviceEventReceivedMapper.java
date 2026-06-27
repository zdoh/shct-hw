package ru.zdoher.events.collector.kafka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.zdoher.events.collector.domain.Device;
import ru.zdoher.events.collector.domain.DeviceEventReceived;

import com.proselyte.platform.events.avro.DeviceEvent;
import com.proselyte.platform.events.avro.DeviceSnapshot;
import com.proselyte.platform.events.avro.SensorSnapshot;
import ru.zdoher.events.collector.domain.Sensor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DeviceEventReceivedMapper {

    @Mapping(target = "eventId", source = "event.eventId")
    @Mapping(target = "eventType", source = "event.eventType")
    @Mapping(target = "eventTimeMs", source = "event.eventTimeMs")
    @Mapping(target = "sourceSystem", source = "event.sourceSystem")
    @Mapping(target = "payload", source = "event.payload")
    @Mapping(target = "device", source = "event.device")
    @Mapping(target = "eventDate", source = "receivedDate")
    @Mapping(target = "ingestedAt", source = "ingestedAt")
    DeviceEventReceived from(DeviceEvent event, LocalDate receivedDate, OffsetDateTime ingestedAt);

    Device from(DeviceSnapshot deviceSnapshot);

    Sensor from(SensorSnapshot sensorSnapshot);

}
