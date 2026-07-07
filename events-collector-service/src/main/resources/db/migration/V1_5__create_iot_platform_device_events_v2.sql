CREATE TABLE IF NOT EXISTS iot_platform.device_events
(
    device_id       String,
    event_date      Date,
    timestamp_ms    Int64,
    event_id        String,
    event_type      LowCardinality(String),
    event_time_ms   Int64,
    source_system   String,
    ingested_at     DateTime64(3, 'UTC'),
    payload         String,
    tenant_id       String,
    owner_id        Nullable(String),
    device_type     String,
    serial_number   Nullable(String),
    manufacturer    Nullable(String),
    model           Nullable(String),
    firmware_version    Nullable(String),
    status          LowCardinality(String),
    registered_at_ms    Int64,
    last_seen_at_ms     Int64,
    settings        Map(String, String),
    attributes      Map(String, String),
    sensors         Array(Tuple(
        sensor_id       String,
        sensor_type     String,
        unit            Nullable(String),
        value           Nullable(String),
        measured_at_ms  Nullable(Int64),
        status          LowCardinality(String)
        )
    )
)
    ENGINE = ReplacingMergeTree(ingested_at)
        PARTITION BY event_date
        ORDER BY (device_id, event_date, timestamp_ms, event_id);