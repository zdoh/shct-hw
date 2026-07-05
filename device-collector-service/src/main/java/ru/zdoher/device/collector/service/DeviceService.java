package ru.zdoher.device.collector.service;

import io.github.robsonkades.uuidv7.UUIDv7;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.zdoher.device.collector.domain.Device;
import ru.zdoher.device.collector.domain.ExtendedDevice;
import ru.zdoher.device.collector.domain.ExtendedSensor;
import ru.zdoher.device.collector.domain.Sensor;
import ru.zdoher.device.collector.repository.DeviceRepository;
import ru.zdoher.device.collector.repository.SensorRepository;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceService implements DeviceSaver {

    private final DeviceRepository deviceRepository;
    private final SensorRepository sensorRepository;

    @Override
    public Device save(Device device) {
        val extenderDevice = buildExtendedDevice(device);
        deviceRepository.save(extenderDevice);
        sensorRepository.saveAll(
          device.sensors()
            .stream()
            .map(sensor -> buildExtendedSensor(
              sensor,
              extenderDevice.id(),
              extenderDevice.device().externalDeviceId())
            )
            .toList()
        );

        return device;
    }

    private ExtendedDevice buildExtendedDevice(Device device) {
        return ExtendedDevice.builder()
          .id(UUIDv7.randomUUID())
          .createdAt(OffsetDateTime.now())
          .device(device)
          .build();
    }

    private ExtendedSensor buildExtendedSensor(Sensor sensor, UUID deviceId, String externalDeviceId) {
        return ExtendedSensor.builder()
          .id(UUIDv7.randomUUID())
          .deviceId(deviceId)
          .externalDeviceId(externalDeviceId)
          .sensor(sensor)
          .build();
    }
}
