package ru.zdoher.device.collector.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.zdoher.device.collector.domain.ExtendedSensor;
import ru.zdoher.device.collector.domain.SensorState;
import ru.zdoher.device.collector.repository.entity.SensorEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface SensorEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "deviceId", source = "deviceId")
    @Mapping(target = "externalDeviceId", source = "externalDeviceId")
    @Mapping(target = "sensorId", source = "sensor.sensorId")
    @Mapping(target = "sensorType", source = "sensor.sensorType")
    @Mapping(target = "unit", source = "sensor.unit")
    @Mapping(target = "value", source = "sensor.value")
    @Mapping(target = "measuredAtMs", source = "sensor.measuredAtMs")
    @Mapping(target = "state", source = "sensor.status", qualifiedByName = "fromStatus")
    SensorEntity from(ExtendedSensor extendedSensor);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "deviceId", source = "deviceId")
    @Mapping(target = "externalDeviceId", source = "externalDeviceId")
    @Mapping(target = "sensor.sensorId", source = "sensorId")
    @Mapping(target = "sensor.sensorType", source = "sensorType")
    @Mapping(target = "sensor.unit", source = "unit")
    @Mapping(target = "sensor.value", source = "value")
    @Mapping(target = "sensor.measuredAtMs", source = "measuredAtMs")
    @Mapping(target = "sensor.status", source = "state", qualifiedByName = "toStatus")
    ExtendedSensor to(SensorEntity entity);

    @Named("fromStatus")
    default String fromStatus(SensorState sensorState) {
        return sensorState.name();
    }

    @Named("toStatus")
    default SensorState toStatus(String state) {
        return SensorState.valueOf(state);
    }
}