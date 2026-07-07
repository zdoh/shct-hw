CREATE TABLE IF NOT EXISTS device_collector_service.device (
    id UUID PRIMARY KEY,
    device_id VARCHAR(36) NOT NULL,
    state VARCHAR(36) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    tenant_id VARCHAR(36) NOT NULL,
    owner_id VARCHAR(36),
    device_type VARCHAR(36) NOT NULL,
    serial_number VARCHAR(36),
    manufacturer VARCHAR(36),
    model VARCHAR(36),
    firmware_version VARCHAR(36),
    registered_at_ms TIMESTAMPTZ
);

COMMENT ON TABLE device_collector_service.device IS 'Сведения о задачах на отправку документа';
COMMENT ON COLUMN device_collector_service.device.id IS 'Уникальный внутренний идентификатор устройства';
COMMENT ON COLUMN device_collector_service.device.device_id IS 'Идентификатор устройства';
COMMENT ON COLUMN device_collector_service.device.state IS 'Состояние устройства';
COMMENT ON COLUMN device_collector_service.device.created_at IS 'Дата и время создания записи';