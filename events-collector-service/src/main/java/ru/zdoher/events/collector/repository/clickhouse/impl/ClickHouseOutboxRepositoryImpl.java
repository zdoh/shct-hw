package ru.zdoher.events.collector.repository.clickhouse.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.zdoher.events.collector.repository.clickhouse.ClickHouseOutboxRepository;
import ru.zdoher.events.collector.repository.model.DeviceOutbox;

import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClickHouseOutboxRepositoryImpl implements ClickHouseOutboxRepository {

    private final RowMapper<DeviceOutbox> deviceOutboxRowMapper;

    private final static String INSERT_SQL = "INSERT INTO iot_platform.device_outbox " +
      " (event_id, source_timestamp_ms, created_at, updated_at, state_version, status, attempts, last_error, payload)" +
      "        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private final static String GET_UNSENT = """
      SELECT event_id,\s
             source_timestamp_ms,
             created_at,
             updated_at,
             state_version,
             status,
             attempts,
             last_error,
             payload
       FROM iot_platform.device_outbox FINAL
       WHERE status IN ('NEW', 'FAILED')
        AND attempts < 10
       ORDER BY created_at
       LIMIT 100;""";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public DeviceOutbox save(DeviceOutbox deviceOutbox) {
        return jdbcTemplate.execute(INSERT_SQL, (PreparedStatementCallback<DeviceOutbox>) ps -> {
              ps.setString(1, deviceOutbox.eventId());
              ps.setLong(2, deviceOutbox.sourceTimestampMs());
              ps.setTimestamp(3, Timestamp.from(deviceOutbox.createdAt().toInstant()));
              ps.setTimestamp(4, Timestamp.from(deviceOutbox.updatedAt().toInstant()));
              ps.setLong(5, deviceOutbox.stateVersion());
              ps.setInt(6, deviceOutbox.status().ordinal());
              ps.setInt(7, deviceOutbox.attempts());
              ps.setString(8, deviceOutbox.lastError());
              ps.setString(9, deviceOutbox.payload());

              ps.execute();

              return deviceOutbox;
          }
        );
    }

    @Override
    public List<DeviceOutbox> getUnsent() {
        return jdbcTemplate.query(GET_UNSENT, deviceOutboxRowMapper);
    }
}
