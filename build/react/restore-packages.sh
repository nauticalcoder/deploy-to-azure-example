#!/usr/bin/env bash

SCRIPT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
cd "$SCRIPT_DIR"

# Add additional lines to run additional test projects in your solution
cd ../../src/<Project Name>.React && npm install && cd "$SCRIPT_DIR"

