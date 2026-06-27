package ru.zdoher.events.collector.repository.mapper;

import org.mapstruct.Mapper;
import ru.zdoher.events.collector.domain.Device;
import ru.zdoher.events.collector.domain.DeviceEventOutbox;
import ru.zdoher.events.collector.domain.Sensor;
import ru.zdoher.events.collector.repository.model.DeviceOutbox;
import ru.zdoher.events.collector.repository.model.DeviceRecord;
import ru.zdoher.events.collector.repository.model.SensorRecord;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DeviceOutboxMapper {

    DeviceOutbox from(DeviceEventOutbox deviceEventOutbox);

    DeviceRecord from(Device device);

    SensorRecord from(Sensor sensor);

    DeviceEventOutbox to(DeviceOutbox deviceOutbox);

    Device to(DeviceRecord device);

    Sensor to(SensorRecord sensor);
}
