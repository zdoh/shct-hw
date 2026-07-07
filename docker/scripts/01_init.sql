-- 1. Создаём пользователя
CREATE USER "user" WITH password 'user';
-- 2. Создаём базу данных
CREATE DATABASE device_collector_service;
-- 3. Даём права на базу
GRANT ALL PRIVILEGES ON DATABASE device_collector_service to "user";
-- 4. Переключаемся на базу device_collector_service (от имени суперпользователя postgres, без указания пользователя)
\c device_collector_service user
-- 5. Создаём схему
CREATE SCHEMA IF NOT EXISTS device_collector_service;
-- 6. Создаём таблицу device
CREATE TABLE IF NOT EXISTS device_collector_service.device (
    id UUID PRIMARY KEY,
    external_device_id VARCHAR(36) NOT NULL,
    state VARCHAR(36) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    tenant_id VARCHAR(36) NOT NULL,
    owner_id VARCHAR(36),
    device_type VARCHAR(36) NOT NULL,
    serial_number VARCHAR(36),
    manufacturer VARCHAR(36),
    model VARCHAR(36),
    firmware_version VARCHAR(36),
    registered_at_ms BIGINT
--    settings jsonb NOT NULL,
--    attributes jsonb NOT NULL
);

COMMENT ON TABLE device_collector_service.device IS 'Сведения о задачах на отправку документа';
COMMENT ON COLUMN device_collector_service.device.id IS 'Уникальный внутренний идентификатор устройства';
COMMENT ON COLUMN device_collector_service.device.external_device_id IS 'Идентификатор устройства';
COMMENT ON COLUMN device_collector_service.device.state IS 'Состояние устройства';
COMMENT ON COLUMN device_collector_service.device.created_at IS 'Дата и время создания записи';
-- 7. Создаём таблицу sensor
CREATE TABLE IF NOT EXISTS device_collector_service.sensor (
    id UUID PRIMARY KEY,
    device_id UUID NOT NULL,
    external_device_id VARCHAR(36) NOT NULL,
    sensor_id VARCHAR(36) NOT NULL,
    sensor_type VARCHAR(36) NOT NULL,
    unit VARCHAR(36),
    value VARCHAR(36),
    measured_at_ms BIGINT,
    state VARCHAR(36) NOT NULL,
    manufacturer VARCHAR(36),
    CONSTRAINT fk_device_id FOREIGN KEY (device_id) REFERENCES device_collector_service.device(id)
);

COMMENT ON COLUMN device_collector_service.sensor.id IS 'Уникальный внутренний идентификатор сенсора';
COMMENT ON COLUMN device_collector_service.sensor.device_id IS 'Внутренний идентификатор связанного устройства';
-- 8. Создаем таблицу processed_device_event
CREATE TABLE IF NOT EXISTS device_collector_service.processed_device_event (
    event_id UUID PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL
);
-- 9. (Опционально) Даём права на таблицу пользователю "user"
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA device_collector_service TO "user";