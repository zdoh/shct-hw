package ru.zdoher.events.collector.repository.mapper;

import org.mapstruct.Mapper;
import ru.zdoher.events.collector.domain.DeviceEventOutbox;
import ru.zdoher.events.collector.repository.model.DeviceOutbox;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DeviceOutboxMapper {

    DeviceOutbox from(DeviceEventOutbox deviceEventOutbox);

    DeviceEventOutbox to(DeviceOutbox deviceOutbox);
}
