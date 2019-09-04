#!/usr/bin/env bash
set -e

GREEN='\033[0;32m'
NC='\033[0m'

USAGE="Usage: ./create_db <ROOT_PATH>"
ROOT_PATH="$1"

if [ -z "$ROOT_PATH" ]
then
    echo "[ERROR]: Provide ROOT_PATH"
    echo "$USAGE"
    exit 1
fi

DB_PATH="$ROOT_PATH/courier.db"
CREATE_SCRIPT_PATH="$ROOT_PATH/src/main/sql/scripts/db_create.sql"
INSERT_SCRIPT_PATH="$ROOT_PATH/src/main/sql/scripts/db_insert.sql"

sqlite3 "$DB_PATH" < "$CREATE_SCRIPT_PATH"
sqlite3 "$DB_PATH" < "$INSERT_SCRIPT_PATH"
