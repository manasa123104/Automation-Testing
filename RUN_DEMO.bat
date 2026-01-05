@echo off
REM Automation Testing - Quick Demo Script (Windows)
REM This script demonstrates how to run the automation tests

echo.
echo 🚀 Automation Testing - Demo Script
echo ====================================
echo.

REM Check if Node.js is installed
where node >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Node.js is not installed. Please install Node.js first.
    exit /b 1
)

echo ✅ Node.js version:
node --version
echo ✅ npm version:
npm --version
echo.

REM Check if dependencies are installed
if not exist "node_modules" (
    echo 📦 Installing dependencies...
    call npm install
    echo.
)

echo 🧪 Running JavaScript Tests...
echo -------------------------------
call npm test tests\javascript\apple-signin-test.js
echo.

echo ⚡ Running Performance Tests...
echo --------------------------------
call npm run test:performance
echo.

echo 📊 Running Tests with Coverage...
echo --------------------------------
call npm run test:coverage
echo.

echo ✅ Demo completed!
echo.
echo 📝 For more information, see: docs\HOW_TO_RUN_TESTS.md
pause

