package ru.zdoher.events.collector.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.zdoher.events.collector.domain.Device;
import ru.zdoher.events.collector.domain.DeviceEventReceived;
import ru.zdoher.events.collector.domain.Sensor;
import ru.zdoher.events.collector.repository.model.DeviceEventRecord;
import ru.zdoher.events.collector.repository.model.DeviceRecord;
import ru.zdoher.events.collector.repository.model.SensorRecord;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DeviceEventMapper {

    @Mapping(target = "deviceId", source = "device.deviceId")
    DeviceEventRecord from(DeviceEventReceived deviceEventReceived);

    DeviceRecord from(Device device);

    SensorRecord from(Sensor sensor);
}
