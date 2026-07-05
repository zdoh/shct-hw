#!/bin/bash
# restore-all-shards.sh

BACKUP_DIR=$1
if [ -z "$BACKUP_DIR" ]; then
    echo "ERROR: Backup directory not set. Script use: ./restore-all-shards.sh DIR_WITH_BACKUP"
    exit 1
fi

if [ ! -d "$BACKUP_DIR" ]; then
    echo "ERROR: Backup directory not found: $BACKUP_DIR"
    exit 1
fi

# Настройки подключения к целевым шардам (чистые контейнеры)
HOST1="localhost"
PORT1="5433"
HOST2="localhost"
PORT2="5434"
GLOBAL_USER="postgres"

# Найти файлы globals и шардов
GLOBALS_FILE=$(ls $BACKUP_DIR/globals_*.sql | head -1)
SHARD1_FILE=$(ls $BACKUP_DIR/shard1_*.sql | head -1)
SHARD2_FILE=$(ls $BACKUP_DIR/shard2_*.sql | head -1)

if [ -z "$GLOBALS_FILE" ] || [ -z "$SHARD1_FILE" ] || [ -z "$SHARD2_FILE" ]; then
    echo "ERROR: Missing required backup files"
    exit 1
fi

echo "Starting restore from $BACKUP_DIR"
echo "Using globals: $GLOBALS_FILE"
echo "Using shard1: $SHARD1_FILE"
echo "Using shard2: $SHARD2_FILE"

# 1. Восстановить глобальные роли (пользователей) на каждом шарде
#    Роли должны быть созданы в каждом кластере (шарде)
for PORT in $PORT1 $PORT2; do
    echo "Restoring global roles on port $PORT..."
    psql -h localhost -p $PORT -U $GLOBAL_USER -d postgres -f $GLOBALS_FILE
    if [ $? -ne 0 ]; then
        echo "ERROR: Failed to restore global roles on port $PORT"
        exit 1
    fi
done

# 2. Восстановить базу данных шарда 1
echo "Restoring shard 1 database..."
psql -h $HOST1 -p $PORT1 -U $GLOBAL_USER -d postgres -f $SHARD1_FILE
if [ $? -ne 0 ]; then
    echo "ERROR: Failed to restore shard 1"
    exit 1
fi

# 3. Восстановить базу данных шарда 2
echo "Restoring shard 2 database..."
psql -h $HOST2 -p $PORT2 -U $GLOBAL_USER -d postgres -f $SHARD2_FILE
if [ $? -ne 0 ]; then
    echo "ERROR: Failed to restore shard 2"
    exit 1
fi

echo "Restore completed successfully."