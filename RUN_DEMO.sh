#!/bin/bash

# Automation Testing - Quick Demo Script
# This script demonstrates how to run the automation tests

echo "🚀 Automation Testing - Demo Script"
echo "===================================="
echo ""

# Check if Node.js is installed
if ! command -v node &> /dev/null; then
    echo "❌ Node.js is not installed. Please install Node.js first."
    exit 1
fi

echo "✅ Node.js version: $(node --version)"
echo "✅ npm version: $(npm --version)"
echo ""

# Check if dependencies are installed
if [ ! -d "node_modules" ]; then
    echo "📦 Installing dependencies..."
    npm install
    echo ""
fi

echo "🧪 Running JavaScript Tests..."
echo "-------------------------------"
npm test tests/javascript/apple-signin-test.js
echo ""

echo "⚡ Running Performance Tests..."
echo "--------------------------------"
npm run test:performance
echo ""

echo "📊 Running Tests with Coverage..."
echo "---------------------------------"
npm run test:coverage
echo ""

echo "✅ Demo completed!"
echo ""
echo "📝 For more information, see: docs/HOW_TO_RUN_TESTS.md"

