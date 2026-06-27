package ru.zdoher.events.collector.repository.clickhouse.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import ru.zdoher.events.collector.repository.clickhouse.ClickHouseEventRepository;
import ru.zdoher.events.collector.repository.model.DeviceEventRecord;
import ru.zdoher.events.collector.repository.model.SensorRecord;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ClickHouseEventRepositoryImpl implements ClickHouseEventRepository {

    private final static String INSERT_SQL = "INSERT INTO device_events " +
      " (device_id, event_date, timestamp_ms, event_id, event_type, event_time_ms, source_system, ingested_at, " +
      " payload, tenant_id, owner_id, device_type, serial_number, manufacturer, model, firmware_version, status, " +
      " registered_at_ms, last_seen_at_ms, settings, attributes, sensors) " +
      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public DeviceEventRecord save(DeviceEventRecord record) {
        return jdbcTemplate.execute(INSERT_SQL, (PreparedStatementCallback<DeviceEventRecord>) ps -> {
              ps.setString(1, record.deviceId());
              ps.setDate(2, Date.valueOf(record.eventDate()));
              ps.setLong(3, record.eventTimeMs());
              ps.setString(4, record.eventId());
              ps.setString(5, record.eventType().name());
              ps.setLong(6, record.eventTimeMs());
              ps.setString(7, record.sourceSystem());
              ps.setTimestamp(8, Timestamp.from(record.ingestedAt().toInstant()));
              ps.setString(9, record.payload());

              var device = record.device();
              ps.setString(10, device.tenantId());
              ps.setString(11, device.ownerId());
              ps.setString(12, device.deviceType());
              ps.setString(13, device.serialNumber());
              ps.setString(14, device.manufacturer());
              ps.setString(15, device.model());
              ps.setString(16, device.firmwareVersion());
              ps.setString(17, device.status().name());
              ps.setLong(18, device.registeredAtMs());
              ps.setLong(19, device.lastSeenAtMs());

              ps.setObject(20, device.settings() != null ? device.settings() : Map.of());
              ps.setObject(21, device.attributes() != null ? device.attributes() : Map.of());

              ps.setObject(22, toSensorsLiteral(device.sensors()));

              ps.execute();

              return record;
          }
        );
    }

    private String toSensorsLiteral(List<SensorRecord> sensors) {
        if (sensors == null || sensors.isEmpty()) {
            return "[]";
        }
        return sensors.stream()
          .map(s -> {
              // Обрабатываем null-значения: для Nullable полей передаём NULL, если значение null
              String unit = s.unit() != null ? "'" + escape(s.unit()) + "'" : "NULL";
              String value = s.value() != null ? "'" + escape(s.value()) + "'" : "NULL";
              String measuredAt = s.measuredAtMs() != null ? s.measuredAtMs().toString() : "NULL";
              String status = s.status() != null ? "'" + s.status().name() + "'" : "NULL";
              return String.format("('%s', '%s', %s, %s, %s, %s)",
                escape(s.sensorId()),
                escape(s.sensorType()),
                unit,
                value,
                measuredAt,
                status
              );
          })
          .collect(Collectors.joining(", ", "[", "]"));
    }

    private String escape(String s) {
        return s.replace("'", "''");
    }
}
