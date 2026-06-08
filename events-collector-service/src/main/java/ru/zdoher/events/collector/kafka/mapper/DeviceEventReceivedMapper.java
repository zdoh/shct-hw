package ru.zdoher.events.collector.kafka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.zdoher.events.collector.domain.DeviceEventReceived;

import com.proselyte.platform.events.avro.DeviceEvent;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DeviceEventReceivedMapper {

    @Mapping(target = "eventId", source = "event.eventId")
    @Mapping(target = "deviceId", source = "event.deviceId")
    @Mapping(target = "timestampMs", source = "event.timestampMs")
    @Mapping(target = "eventType", source = "event.eventType")
    @Mapping(target = "payload", source = "event.payload")
    @Mapping(target = "eventDate", source = "receivedDate")
    @Mapping(target = "ingestedAt", source = "ingestedAt")
    DeviceEventReceived from(DeviceEvent event, LocalDate receivedDate, OffsetDateTime ingestedAt);


}
