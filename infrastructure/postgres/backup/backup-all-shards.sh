#!/bin/bash
# backup-all-shards.sh

BACKUP_DIR="./postgres"
TIMESTAMP=$(date +"%Y%m%d_%H%M%S")
HOST1="localhost"
PORT1="5433"
HOST2="localhost"
PORT2="5434"
DB_NAME="device_collector_service"
GLOBAL_USER="postgres"

mkdir -p $BACKUP_DIR

echo "Starting backup at $TIMESTAMP"

# 1. Экспорт глобальных ролей (пользователей)
pg_dumpall -h $HOST1 -p $PORT1 -U $GLOBAL_USER --globals-only -f "$BACKUP_DIR/globals_$TIMESTAMP.sql"
if [ $? -eq 0 ]; then
    echo "Global roles (users) exported: globals_$TIMESTAMP.sql"
else
    echo "ERROR: Failed to export global roles"
    exit 1
fi

# 2. Дамп шарда 1 (с CREATE DATABASE)
pg_dump -h $HOST1 -p $PORT1 -U $GLOBAL_USER -d $DB_NAME -F p -C -b -v -f "$BACKUP_DIR/shard1_$TIMESTAMP.sql"
if [ $? -eq 0 ]; then
    echo "Shard 1 backup successful: shard1_$TIMESTAMP.sql"
else
    echo "ERROR: Shard 1 backup failed"
    exit 1
fi

# 3. Дамп шарда 2 (с CREATE DATABASE)
pg_dump -h $HOST2 -p $PORT2 -U $GLOBAL_USER -d $DB_NAME -F p -C -b -v -f "$BACKUP_DIR/shard2_$TIMESTAMP.sql"
if [ $? -eq 0 ]; then
    echo "Shard 2 backup successful: shard2_$TIMESTAMP.sql"
else
    echo "ERROR: Shard 2 backup failed"
    exit 1
fi

echo "All backups completed at $(date +"%Y%m%d_%H%M%S")"