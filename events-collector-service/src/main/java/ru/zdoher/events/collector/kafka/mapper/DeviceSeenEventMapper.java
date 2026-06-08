package ru.zdoher.events.collector.kafka.mapper;

import org.mapstruct.Mapper;
import ru.zdoher.events.collector.domain.DeviceEventOutbox;
import com.proselyte.platform.events.avro.DeviceSeenEvent;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DeviceSeenEventMapper {

    DeviceSeenEvent from(DeviceEventOutbox deviceEventOutbox);
}
