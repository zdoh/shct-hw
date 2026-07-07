package ru.zdoher.events.collector.repository.clickhouse.mapping;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.zdoher.events.collector.domain.DeviceEventOutboxStatus;
import ru.zdoher.events.collector.repository.model.DeviceOutbox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

@Component
public class DeviceOutboxRowMapper implements RowMapper<DeviceOutbox> {

    private static final String EVENT_ID_COLUMN_NAME = "event_id";
    private static final String SOURCE_TIMESTAMP_MS_COLUMN_NAME = "source_timestamp_ms";
    private static final String CREATE_AT_COLUMN_NAME = "created_at";
    private static final String UPDATE_AT_COLUMN_NAME = "updated_at";
    private static final String STATE_VERSION_COLUMN_NAME = "state_version";
    private static final String STATUS_COLUMN_NAME = "status";
    private static final String ATTEMPTS_COLUMN_NAME = "attempts";
    private static final String LAST_ERROR_COLUMN_NAME = "last_error";
    private static final String PAYLOAD_COLUMN_NAME = "payload";

    @Override
    public DeviceOutbox mapRow(ResultSet rs, int rowNum) throws SQLException {
        return DeviceOutbox.builder()
          .eventId(rs.getString(EVENT_ID_COLUMN_NAME))
          .sourceTimestampMs(rs.getLong(SOURCE_TIMESTAMP_MS_COLUMN_NAME))
          .createdAt(rs.getObject(CREATE_AT_COLUMN_NAME, OffsetDateTime.class))
          .updatedAt(rs.getObject(UPDATE_AT_COLUMN_NAME, OffsetDateTime.class))
          .stateVersion(rs.getLong(STATE_VERSION_COLUMN_NAME))
          .status(
            DeviceEventOutboxStatus.valueOf(
              rs.getString(STATUS_COLUMN_NAME)
            )
          )
          .attempts(rs.getInt(ATTEMPTS_COLUMN_NAME))
          .lastError(rs.getString(LAST_ERROR_COLUMN_NAME))
          .payload(rs.getString(PAYLOAD_COLUMN_NAME))
          .build();
    }
}
