package ru.zdoher.events.collector.repository.clickhouse.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.zdoher.events.collector.repository.clickhouse.ClickHouseOutboxRepository;
import ru.zdoher.events.collector.repository.model.DeviceOutbox;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClickHouseOutboxRepositoryImpl implements ClickHouseOutboxRepository {

    private final RowMapper<DeviceOutbox> deviceOutboxRowMapper;

    private final static String INSERT_SQL = "INSERT INTO iot_platform.device_outbox " +
      " (event_id, device_id, source_timestamp_ms, created_at, updated_at, state_version, status, attempts, last_error)" +
      "        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private final static String GET_UNSENT = """
      SELECT event_id,\s
             device_id,
             source_timestamp_ms,
             created_at,
             updated_at,
             state_version,
             status,
             attempts,
             last_error
       FROM iot_platform.device_outbox FINAL
       WHERE status IN ('NEW', 'FAILED')
        AND attempts < 10
       ORDER BY created_at
       LIMIT 100;""";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public DeviceOutbox save(DeviceOutbox deviceOutbox) {
        jdbcTemplate.update(
          INSERT_SQL,
          deviceOutbox.eventId(),
          deviceOutbox.deviceId(),
          deviceOutbox.sourceTimestampMs(),
          deviceOutbox.createdAt(),
          deviceOutbox.updatedAt(),
          deviceOutbox.stateVersion(),
          deviceOutbox.status().ordinal(),
          deviceOutbox.attempts(),
          deviceOutbox.lastError()
        );

        return deviceOutbox;
    }

    @Override
    public List<DeviceOutbox> getUnsent() {
        return jdbcTemplate.query(GET_UNSENT, deviceOutboxRowMapper);
    }
}
