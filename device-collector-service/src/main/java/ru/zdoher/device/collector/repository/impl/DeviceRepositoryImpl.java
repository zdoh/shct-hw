package ru.zdoher.device.collector.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.zdoher.device.collector.domain.ExtendedDevice;
import ru.zdoher.device.collector.repository.DeviceRepository;
import ru.zdoher.device.collector.repository.jpa.DeviceJpaRepository;
import ru.zdoher.device.collector.repository.mapper.DeviceEntityMapper;

@Repository
@RequiredArgsConstructor
public class DeviceRepositoryImpl implements DeviceRepository {

    private final DeviceEntityMapper deviceEntityMapper;
    private final DeviceJpaRepository deviceJpaRepository;

    @Override
    public ExtendedDevice save(ExtendedDevice extendedDevice) {
        return deviceEntityMapper.to(
          deviceJpaRepository.save(
            deviceEntityMapper.from(
              extendedDevice
            )
          )
        );
    }
}
