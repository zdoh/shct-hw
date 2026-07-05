#!/bin/bash
set -e

# Ждем, пока мастер поднимется
echo "Waiting for master ($MASTER_HOST) to be ready..."
until pg_isready -h "$MASTER_HOST" -U "$POSTGRES_USER"; do
    sleep 2
done

# Останавливаем PostgreSQL, чтобы очистить данные реплики
pg_ctl -D "$PGDATA" -m fast -w stop

# Очищаем директорию данных реплики
rm -rf "$PGDATA"/*

# Запускаем pg_basebackup для клонирования данных с мастера
echo "Running pg_basebackup from $MASTER_HOST..."
PGPASSWORD=$POSTGRES_REPLICATION_PASSWORD pg_basebackup -h "$MASTER_HOST" -U "$POSTGRES_REPLICATION_USER" -D "$PGDATA" -Fp -Xs -P -R

# Запускаем PostgreSQL снова
pg_ctl -D "$PGDATA" -w start