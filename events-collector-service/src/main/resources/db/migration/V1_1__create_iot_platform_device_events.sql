CREATE TABLE IF NOT EXISTS iot_platform.device_events
(
    device_id    String,
    event_id     String,
    event_date   Date,
    timestamp_ms Int64,
    event_type LowCardinality(String),
    payload      String,
    ingested_at  DateTime64(3, 'UTC')
)
    ENGINE = ReplacingMergeTree(ingested_at)
        PARTITION BY event_date
        ORDER BY (device_id, event_date, timestamp_ms, event_id);