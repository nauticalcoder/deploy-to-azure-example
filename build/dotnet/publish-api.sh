#!/usr/bin/env bash

SCRIPT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd -P )
cd "$SCRIPT_DIR"
cd ../../src/<ProjectName>.Api

exec dotnet publish \
  --configuration Release \
  --output ../../artifacts/api

