package ru.zdoher.events.collector.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.zdoher.events.collector.domain.DeviceEventReceived;
import ru.zdoher.events.collector.repository.DeviceEventRepository;
import ru.zdoher.events.collector.repository.clickhouse.ClickHouseEventRepository;
import ru.zdoher.events.collector.repository.mapper.DeviceEventMapper;

@Repository
@RequiredArgsConstructor
public class DeviceEventRepositoryImpl implements DeviceEventRepository {

    private final DeviceEventMapper deviceEventMapper;
    private final ClickHouseEventRepository clickHouseEventRepository;

    @Override
    public DeviceEventReceived save(DeviceEventReceived deviceEventReceived) {
        clickHouseEventRepository.save(
          deviceEventMapper.from(deviceEventReceived)
        );

        return deviceEventReceived;
    }
}
