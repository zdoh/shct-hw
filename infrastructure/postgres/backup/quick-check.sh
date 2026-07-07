#!/bin/bash
# quick-check.sh - Проверка доступности данных через ShardingSphere-Proxy после восстановления

# === Настройки подключения к Proxy ===
PROXY_HOST="localhost"
PROXY_PORT="13308"
PROXY_USER="someuser"
PROXY_PASSWORD="someuser"
PROXY_DB="device_collector_service"
SCHEMA="device_collector_service"

# === Проверка ===
echo "Running quick check on ShardingSphere-Proxy..."

# Выполняем запрос: количество записей в таблице device
QUERY="SELECT COUNT(*) FROM "$SCHEMA".device;"
OUTPUT=$(psql -h "$PROXY_HOST" -p "$PROXY_PORT" -U "$PROXY_USER" -d "$PROXY_DB" -t -c "$QUERY" 2>&1)
EXIT_CODE=$?

# Проверяем результат
if [ $EXIT_CODE -ne 0 ]; then
    echo "FAILED: Could not connect to ShardingSphere-Proxy or query execution failed."
    echo "   Error details: $OUTPUT"
    exit 1
fi

# Убираем лишние пробелы и проверяем, что вывод — число
COUNT=$(echo "$OUTPUT" | xargs)
if [[ "$COUNT" =~ ^[0-9]+$ ]]; then
    echo "SUCCESS: Connected to Proxy, table 'device' is accessible."
    echo "   Total records in 'device': $COUNT"
else
    echo "FAILED: Unexpected query result: $OUTPUT"
    exit 1
fi

exit 0