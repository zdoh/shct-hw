package ru.zdoher.device.collector.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.zdoher.device.collector.domain.DeviceState;
import ru.zdoher.device.collector.domain.ExtendedDevice;
import ru.zdoher.device.collector.repository.entity.DeviceEntity;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DeviceEntityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "externalDeviceId", source = "device.externalDeviceId")
    @Mapping(target = "state", source = "device.status", qualifiedByName = "fromState")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "tenantId", source = "device.tenantId")
    @Mapping(target = "ownerId", source = "device.ownerId")
    @Mapping(target = "deviceType", source = "device.deviceType")
    @Mapping(target = "serialNumber", source = "device.serialNumber")
    @Mapping(target = "manufacturer", source = "device.manufacturer")
    @Mapping(target = "model", source = "device.model")
    @Mapping(target = "firmwareVersion", source = "device.firmwareVersion")
    @Mapping(target = "registeredAtMs", source = "device.registeredAtMs")
//    @Mapping(target = "settings", source = "device.settings")
//    @Mapping(target = "attributes", source = "device.attributes")
    DeviceEntity from(ExtendedDevice extendedDevice);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "device.externalDeviceId", source = "externalDeviceId")
    @Mapping(target = "device.status", source = "state", qualifiedByName = "toState")
    @Mapping(target = "device.tenantId", source = "tenantId")
    @Mapping(target = "device.ownerId", source = "ownerId")
    @Mapping(target = "device.deviceType", source = "deviceType")
    @Mapping(target = "device.serialNumber", source = "serialNumber")
    @Mapping(target = "device.manufacturer", source = "manufacturer")
    @Mapping(target = "device.model", source = "model")
    @Mapping(target = "device.firmwareVersion", source = "firmwareVersion")
    @Mapping(target = "device.registeredAtMs", source = "registeredAtMs")
//    @Mapping(target = "device.settings", source = "settings")
//    @Mapping(target = "device.attributes", source = "attributes")
    @Mapping(target = "createdAt", source = "createdAt")
    ExtendedDevice to(DeviceEntity deviceEntity);

    @Named("fromState")
    default String fromState(DeviceState deviceState) {
        return deviceState.name();
    }

    @Named("toState")
    default DeviceState toState(String state) {
        return DeviceState.valueOf(state);
    }
}
