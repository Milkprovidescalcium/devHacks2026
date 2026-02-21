#!/usr/bin/env bash
set -e

echo "[1/3] Starting Python Backend..."
./backend/venv/bin/python backend/bridge.py &
PYTHON_PID=$!

echo "[2/3] Compiling Java Files..."
javac frontend/src/*.java
if [ $? -ne 0 ]; then
  echo "Compilation failed!"
  kill "$PYTHON_PID" || true
  exit 1
fi

echo "[3/3] Launching Java Frontend..."
java -cp frontend/src:frontend/resources MainApp

echo "Shutting down Python..."
kill "$PYTHON_PID" || true
