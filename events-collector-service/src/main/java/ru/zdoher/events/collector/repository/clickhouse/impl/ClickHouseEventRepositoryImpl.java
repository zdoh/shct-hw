package ru.zdoher.events.collector.repository.clickhouse.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.zdoher.events.collector.repository.clickhouse.ClickHouseEventRepository;
import ru.zdoher.events.collector.repository.model.DeviceEvent;

@Repository
@RequiredArgsConstructor
public class ClickHouseEventRepositoryImpl implements ClickHouseEventRepository {

    private final static String INSERT_SQL = "INSERT INTO device_events " +
      " (device_id, event_id, event_date, timestamp_ms, event_type, payload, ingested_at)" +
      "        VALUES (?, ?, ?, ?, ?, ?, ?);";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public DeviceEvent save(DeviceEvent deviceEvent) {
        jdbcTemplate.update(
          INSERT_SQL,
          deviceEvent.deviceId(),
          deviceEvent.eventId(),
          deviceEvent.eventDate(),
          deviceEvent.timestampMs(),
          deviceEvent.eventType(),
          deviceEvent.payload(),
          deviceEvent.ingestedAt()
        );

        return deviceEvent;
    }
}
