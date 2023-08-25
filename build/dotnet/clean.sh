#!/usr/bin/env bash

SCRIPT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd -P )
cd "$SCRIPT_DIR"

if [ -d "../../artifacts" ]; then
  rm -rf ../../artifacts
fi

cd ../../src
exec dotnet clean --configuration Release
exec dotnet clean --configuration Debug

