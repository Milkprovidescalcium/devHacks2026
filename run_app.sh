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
# -cp . tells Java to look in the current folder for the 'frontend' package
# We use 'frontend.MainApp' (no .java extension!)
java -cp frontend/src MainApp

echo "Shutting down Python..."
kill $PYTHON_PID
