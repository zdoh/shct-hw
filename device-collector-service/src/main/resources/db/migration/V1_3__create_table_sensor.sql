CREATE TABLE IF NOT EXISTS device_collector_service.sensor (
    id UUID PRIMARY KEY,
    device_id VARCHAR(36) NOT NULL,
    sensor_id VARCHAR(36) NOT NULL,
    sensor_type VARCHAR(36) NOT NULL,
    unit VARCHAR(36),
    value VARCHAR(36),
    measured_at_ms BIGINT,
    state VARCHAR(36) NOT NULL,
    manufacturer VARCHAR(36),
    CONSTRAINT fk_device_id FOREIGN KEY (device_id) REFERENCES device_collector_service.device(id)
);

COMMENT ON COLUMN sensor.id IS 'Уникальный внутренний идентификатор сенсора';
COMMENT ON COLUMN sensor.device_id IS 'Идентификатор связанного устройства';