# ⚡ Quick Start - Run Tests in 3 Steps

## Step 1: Install Dependencies

```bash
npm install
```

**Expected Output:**
```
added 245 packages in 15s
```

## Step 2: Run Tests

```bash
npm test
```

**Expected Output:**
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

Test Suites: 1 passed, 1 total
Tests:       12 passed, 12 total
```

## Step 3: Run Performance Tests

```bash
npm run test:performance
```

**Expected Output:**
```
PASS  tests/javascript/performance-test.js
  Apple Sign-In Performance Tests
    ✓ app should launch within 3 seconds
    ✓ login screen should load within 2 seconds
    ✓ Apple Sign-In dialog should appear within 2 seconds
    ✓ authentication should complete within 3 seconds
    ✓ complete authentication flow should finish within 10 seconds

Test Suites: 1 passed, 1 total
Tests:       5 passed, 5 total
```

---

## 🎯 Other Useful Commands

```bash
# Run tests in watch mode (auto-rerun on file changes)
npm run test:watch

# Run tests with coverage report
npm run test:coverage

# Run only Apple Sign-In tests
npm run test:apple

# Run specific test case
npm test -- -t "should display Apple Sign-In button"
```

---

## 📱 Platform-Specific Tests

### iOS (macOS only)
1. Open Xcode
2. Add `tests/ios/AppleSignInTests.swift` to your test target
3. Press `Cmd + U` to run

### Android
1. Copy `tests/android/AppleSignInTest.kt` to your Android project
2. Run: `./gradlew connectedAndroidTest`

---

## 📚 Need More Help?

- **Full Demo Guide:** [`docs/HOW_TO_RUN_TESTS.md`](docs/HOW_TO_RUN_TESTS.md)
- **Testing Guide:** [`docs/Testing_Guide_Apple_SignIn.md`](docs/Testing_Guide_Apple_SignIn.md)
- **Quick Checklist:** [`docs/Quick_Testing_Checklist.md`](docs/Quick_Testing_Checklist.md)

---

**That's it! You're ready to test! 🎉**

