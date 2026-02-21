#!/usr/bin/fish

echo "[1/3] Starting Python Backend..."
# Point directly to the python binary inside the venv
./backend/venv/bin/python backend/bridge.py &
set PYTHON_PID $last_pid

echo "[2/3] Compiling Java Files..."
# Compile the specific main file (this will compile dependencies too)
javac frontend/src/*.java

if test $status -ne 0
    echo "Compilation failed!"
    kill $PYTHON_PID
    exit 1
end

echo "[3/3] Launching Java Frontend..."
# Include resources directory on the classpath so getResource("/icon.png") and sounds load
# Use ':' as classpath separator on Unix-like systems
java -cp frontend/src:frontend/resources MainApp

echo "Shutting down Python..."
kill $PYTHON_PID
