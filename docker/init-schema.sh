#!/bin/sh
# init-schema.sh

set -e

echo "Waiting for Schema Registry..."
while ! curl -s -f http://schema-registry:8381/subjects; do
  sleep 2
done
echo "Schema Registry is ready."

# Если есть папка со схемами, регистрируем каждую
if [ -d /schemas ]; then
  for schema_file in /schemas/*.avsc; do
    if [ -f "$schema_file" ]; then
      subject_name=$(basename "$schema_file" .avsc)-value
      echo "Registering $subject_name"
      # Формируем JSON с экранированием
      schema_content=$(cat "$schema_file" | jq -Rs .)
      curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
           --data "{\"schemaType\": \"AVRO\", \"schema\": $schema_content}" \
           http://schema-registry:8381/subjects/$subject_name/versions
      echo ""
    fi
  done
fi

echo "Schema initialization complete."