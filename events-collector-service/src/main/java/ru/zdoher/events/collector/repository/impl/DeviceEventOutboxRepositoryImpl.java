package ru.zdoher.events.collector.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.zdoher.events.collector.domain.DeviceEventOutbox;
import ru.zdoher.events.collector.repository.DeviceEventOutboxRepository;
import ru.zdoher.events.collector.repository.clickhouse.ClickHouseOutboxRepository;
import ru.zdoher.events.collector.repository.mapper.DeviceOutboxMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeviceEventOutboxRepositoryImpl implements DeviceEventOutboxRepository {

    private final DeviceOutboxMapper deviceOutboxMapper;
    private final ClickHouseOutboxRepository clickHouseOutboxRepository;

    @Override
    public DeviceEventOutbox save(DeviceEventOutbox deviceEventOutbox) {
        return deviceOutboxMapper.to(
          clickHouseOutboxRepository.save(
            deviceOutboxMapper.from(deviceEventOutbox)
          )
        );
    }

    @Override
    public List<DeviceEventOutbox> getUnsent() {
        return clickHouseOutboxRepository.getUnsent()
          .stream()
          .map(deviceOutboxMapper::to)
          .toList();
    }
}
