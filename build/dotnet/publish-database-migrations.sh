#!/usr/bin/env bash

SCRIPT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd -P )
cd "$SCRIPT_DIR"
cd ../../src/<ProjectName>.Domain

exec dotnet tool run dotnet-ef -- \
  migrations \
  script \
  --configuration Release \
  --idempotent \
  --output ../../artifacts/database-migrations.sql

