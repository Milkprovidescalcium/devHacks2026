@echo off
echo [1/3] Starting Python Backend...
:: Start the backend in a separate minimized window to keep it running
start /b "" "backend\venv\Scripts\python.exe" backend\bridge.py

echo [2/3] Compiling Java Files...
javac frontend\src\*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    taskkill /f /im python.exe
    exit /b 1
)

echo [3/3] Launching Java Frontend...
java -cp frontend\src MainApp

echo Shutting down Python...
:: This kills the python process started earlier
taskkill /f /im python.exe
