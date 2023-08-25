#!/usr/bin/env bash

SCRIPT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd -P )
cd "$SCRIPT_DIR"
cd ../../src

exec dotnet build --configuration Release

