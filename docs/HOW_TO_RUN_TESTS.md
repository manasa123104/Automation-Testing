# 🚀 How to Run Automation Tests - Demo Guide

This guide demonstrates how to run all the automation tests in this repository.

---

## 📋 Table of Contents

1. [Prerequisites](#prerequisites)
2. [Setup Instructions](#setup-instructions)
3. [Running JavaScript/React Native Tests](#running-javascriptreact-native-tests)
4. [Running iOS Tests](#running-ios-tests)
5. [Running Android Tests](#running-android-tests)
6. [Running Performance Tests](#running-performance-tests)
7. [Troubleshooting](#troubleshooting)

---

## 🔧 Prerequisites

Before running the tests, ensure you have:

- **Node.js** (v16 or higher) - [Download](https://nodejs.org/)
- **npm** (comes with Node.js)
- **For iOS**: Xcode (macOS only)
- **For Android**: Android Studio
- **Git** (to clone the repository)

---

## 📦 Setup Instructions

### Step 1: Clone the Repository

```bash
git clone https://github.com/manasa123104/Automation-Testing.git
cd Automation-Testing
```

### Step 2: Install Dependencies

```bash
npm install
```

**Expected Output:**
```
added 245 packages, and audited 246 packages in 15s
```

### Step 3: Verify Installation

```bash
npm test -- --version
```

**Expected Output:**
```
jest version 29.0.0
```

---

## 🧪 Running JavaScript/React Native Tests

### Option 1: Run All Tests

```bash
npm test
```

**Expected Output:**
```
PASS  tests/javascript/apple-signin-test.js
  Apple Sign-In Authentication
    ✓ should display Apple Sign-In button on login screen (15ms)
    ✓ Apple Sign-In button should be pressable (8ms)
    ✓ should call AppleAuthentication.signInAsync with correct scopes (12ms)
    ...
    
Test Suites: 1 passed, 1 total
Tests:       12 passed, 12 total
```

### Option 2: Run Only Apple Sign-In Tests

```bash
npm run test:apple
```

**Or:**
```bash
npm test tests/javascript/apple-signin-test.js
```

### Option 3: Run Tests in Watch Mode

```bash
npm run test:watch
```

This will automatically re-run tests when files change.

### Option 4: Run Tests with Coverage Report

```bash
npm run test:coverage
```

**Expected Output:**
```
PASS  tests/javascript/apple-signin-test.js
  Apple Sign-In Authentication
    ✓ should display Apple Sign-In button on login screen
    ...

-------------------|---------|----------|---------|---------|-------------------
File               | % Stmts | % Branch | % Funcs | % Lines | Uncovered Line #s
-------------------|---------|----------|---------|---------|-------------------
All files          |   85.23 |    78.45 |   90.12 |   84.56 |
-------------------|---------|----------|---------|---------|-------------------
```

### Option 5: Run Specific Test File

```bash
npx jest tests/javascript/apple-signin-test.js
```

### Option 6: Run Specific Test Case

```bash
npx jest tests/javascript/apple-signin-test.js -t "should display Apple Sign-In button"
```

---

## 📱 Running iOS Tests

### Prerequisites for iOS

1. **macOS** with Xcode installed
2. **Xcode Command Line Tools**:
   ```bash
   xcode-select --install
   ```

### Step 1: Open in Xcode

```bash
# Navigate to your iOS project directory
cd /path/to/your/ios/project

# Open Xcode
open YourApp.xcworkspace
```

### Step 2: Add Test File

1. In Xcode, right-click on your test target
2. Select "Add Files to [Project Name]"
3. Navigate to `tests/ios/AppleSignInTests.swift`
4. Make sure "Copy items if needed" is checked
5. Select your test target in "Add to targets"

### Step 3: Run Tests

**Option A: Using Xcode UI**
- Press `Cmd + U` or
- Go to **Product** → **Test**

**Option B: Using Command Line**

```bash
xcodebuild test \
  -workspace YourApp.xcworkspace \
  -scheme YourApp \
  -destination 'platform=iOS Simulator,name=iPhone 14'
```

**Expected Output:**
```
Test Suite 'AppleSignInTests' started.
Test Case '-[AppleSignInTests testAppleSignInButtonExists]' started.
Test Case '-[AppleSignInTests testAppleSignInButtonExists]' passed (0.001 seconds).
...
Test Suite 'AppleSignInTests' passed at 2025-01-03 21:30:00.000.
     Executed 12 tests, with 0 failures (0 unexpected) in 0.045 (0.050) seconds
```

---

## 🤖 Running Android Tests

### Prerequisites for Android

1. **Android Studio** installed
2. **Android SDK** configured
3. **Gradle** (comes with Android Studio)

### Step 1: Add Test File to Android Project

1. Copy `tests/android/AppleSignInTest.kt` to:
   ```
   android/app/src/androidTest/java/com/yourapp/tests/AppleSignInTest.kt
   ```

2. Update the package name in the test file to match your app:
   ```kotlin
   package com.yourapp.tests  // Change to your package name
   ```

### Step 2: Run Tests

**Option A: Using Gradle Command Line**

```bash
cd /path/to/your/android/project
./gradlew connectedAndroidTest
```

**Or on Windows:**
```bash
gradlew.bat connectedAndroidTest
```

**Expected Output:**
```
> Task :app:connectedDebugAndroidTest
Starting 13 tests on Pixel_5_API_33(AVD) - 13

com.yourapp.tests.AppleSignInTest > testAppleSignInButtonVisible PASSED
com.yourapp.tests.AppleSignInTest > testAppleSignInButtonText PASSED
...

BUILD SUCCESSFUL in 45s
13 actionable tasks: 13 executed
```

**Option B: Using Android Studio**

1. Open your Android project in Android Studio
2. Right-click on `AppleSignInTest.kt`
3. Select **Run 'AppleSignInTest'**

**Option C: Run Specific Test**

```bash
./gradlew connectedAndroidTest --tests "com.yourapp.tests.AppleSignInTest.testAppleSignInButtonVisible"
```

---

## ⚡ Running Performance Tests

### Run Performance Tests

```bash
npm run test:performance
```

**Or:**
```bash
npm test tests/javascript/performance-test.js
```

**Expected Output:**
```
PASS  tests/javascript/performance-test.js
  Apple Sign-In Performance Tests
    ✓ app should launch within 3 seconds (102ms)
      App launch time: 98.45ms
    ✓ login screen should load within 2 seconds (156ms)
      Login screen load time: 152.30ms
    ✓ Apple Sign-In dialog should appear within 2 seconds (201ms)
      Apple dialog appearance time: 198.12ms
    ✓ authentication should complete within 3 seconds (234ms)
      Authentication time: 231.56ms
    ✓ complete authentication flow should finish within 10 seconds (567ms)
      Total flow time: 564.23ms
    ✓ should maintain performance across multiple attempts (890ms)
      Average time: 178.45ms
      Standard deviation: 12.34ms
    ✓ should handle network latency gracefully (1234ms)
      With 0ms network delay: 231.56ms
      With 100ms network delay: 332.45ms
      ...
    ✓ should not cause memory leaks during authentication (2345ms)
      Memory increase: 2.34MB

Test Suites: 1 passed, 1 total
Tests:       8 passed, 8 total
```

---

## 🎯 Quick Demo Commands

### Run All Tests (Quick Demo)

```bash
# Install dependencies
npm install

# Run all JavaScript tests
npm test

# Run with coverage
npm run test:coverage

# Run specific test
npm test -- -t "should display Apple Sign-In button"
```

### Expected Test Results Summary

When you run `npm test`, you should see:

```
PASS  tests/javascript/apple-signin-test.js
  Apple Sign-In Authentication
    ✓ should display Apple Sign-In button on login screen
    ✓ Apple Sign-In button should be pressable
    ✓ should call AppleAuthentication.signInAsync with correct scopes
    ✓ should receive valid credential with identityToken
    ✓ should create OAuthProvider credential for apple.com
    ✓ should authenticate user and redirect to home screen
    ✓ should update auth state correctly
    ✓ should store Apple provider data in authProviders
    ✓ should handle user cancellation gracefully
    ✓ should handle authentication errors
    ✓ should handle email sharing option
    ✓ should handle hide email option

  Apple Sign-In Performance
    ✓ should complete authentication within 5 seconds
    ✓ should load login screen within 2 seconds

Test Suites: 1 passed, 1 total
Tests:       14 passed, 14 total
Snapshots:   0 total
Time:        2.345 s
```

---

## 🔍 Troubleshooting

### Issue: `npm test` fails with "Cannot find module"

**Solution:**
```bash
# Remove node_modules and reinstall
rm -rf node_modules package-lock.json
npm install
```

### Issue: Tests fail because of missing mocks

**Solution:**
The test files use mocks. Make sure you have the required dependencies:
```bash
npm install --save-dev jest @testing-library/react-native
```

### Issue: iOS tests not found in Xcode

**Solution:**
1. Make sure the test file is added to the correct test target
2. Check that the test target is selected in the scheme
3. Clean build folder: `Cmd + Shift + K`
4. Rebuild: `Cmd + B`

### Issue: Android tests fail with "Class not found"

**Solution:**
1. Check that the package name matches your app's package
2. Ensure the test file is in `androidTest` directory, not `test`
3. Sync Gradle: In Android Studio, click **File** → **Sync Project with Gradle Files**

### Issue: Performance tests show slow times

**Solution:**
- This is expected in development environments
- Performance tests use mocks and may not reflect real-world performance
- Adjust thresholds in the test file if needed

---

## 📝 Test File Locations

```
Automation-Testing/
├── tests/
│   ├── javascript/
│   │   ├── apple-signin-test.js      # Main test suite
│   │   └── performance-test.js       # Performance tests
│   ├── ios/
│   │   └── AppleSignInTests.swift    # iOS XCTest cases
│   └── android/
│       └── AppleSignInTest.kt         # Android Espresso tests
├── package.json                       # Dependencies and scripts
└── README.md                          # Project documentation
```

---

## 🎓 Next Steps

1. **Customize Tests**: Modify test files to match your app's implementation
2. **Add More Tests**: Extend the test suite with additional scenarios
3. **CI/CD Integration**: Set up automated testing in your CI/CD pipeline
4. **Review Documentation**: Check `docs/Testing_Guide_Apple_SignIn.md` for detailed testing guide

---

## 📚 Additional Resources

- [Jest Documentation](https://jestjs.io/)
- [React Native Testing Library](https://callstack.github.io/react-native-testing-library/)
- [XCTest Documentation](https://developer.apple.com/documentation/xctest)
- [Espresso Documentation](https://developer.android.com/training/testing/espresso)

---

**Happy Testing! 🎉**

