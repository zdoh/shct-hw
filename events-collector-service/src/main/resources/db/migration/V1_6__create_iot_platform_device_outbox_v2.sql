CREATE TABLE IF NOT EXISTS iot_platform.device_outbox
(
    event_id            String,
    source_timestamp_ms Int64,
    created_at          DateTime64(3, 'UTC'),
    updated_at          DateTime64(3, 'UTC'),
    state_version       UInt64,
    status Enum8('NEW' = 0, 'SENT' = 1, 'FAILED' = 2),
    attempts            UInt32,
    last_error          String,
    payload             String
)
    ENGINE = ReplacingMergeTree(state_version)
        PARTITION BY toYYYYMM(created_at)
        ORDER BY (event_id);