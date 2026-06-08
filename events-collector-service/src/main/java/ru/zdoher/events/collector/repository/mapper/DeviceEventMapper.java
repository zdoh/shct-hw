package ru.zdoher.events.collector.repository.mapper;

import org.mapstruct.Mapper;
import ru.zdoher.events.collector.domain.DeviceEventReceived;
import ru.zdoher.events.collector.repository.model.DeviceEvent;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DeviceEventMapper {

    DeviceEvent from(DeviceEventReceived deviceEventReceived);

    DeviceEventReceived to(DeviceEvent deviceEvent);
}
