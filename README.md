# Automation Test Cases - Apple Sign-In Authentication

Comprehensive automation test documentation and code for testing Apple Sign-In Authentication feature.

## 📋 Overview

This repository contains:
- **Test Documentation**: Complete guides, checklists, and templates
- **Test Code**: Automated test suites for React Native, iOS, and Android
- **Performance Tests**: Performance benchmarking tests
- **Testing Guides**: Step-by-step instructions for manual testing

## 🚀 Quick Start

### For Manual Testing:
1. Use [`Quick_Testing_Checklist.md`](docs/Quick_Testing_Checklist.md) during testing
2. Follow [`Testing_Guide_Apple_SignIn.md`](docs/Testing_Guide_Apple_SignIn.md) for detailed guide

### For Automated Testing:

#### React Native/Expo:
```bash
npm install
npm test apple-signin-test.js
```

#### iOS (Xcode):
- Open `tests/ios/AppleSignInTests.swift` in Xcode
- Add to test target
- Press `Cmd + U` to run

#### Android:
```bash
./gradlew connectedAndroidTest
```

## 📁 Repository Structure

```
├── docs/                          # Documentation
│   ├── Video_Analysis_Notes.md
│   ├── Testing_Guide_Apple_SignIn.md
│   ├── Quick_Testing_Checklist.md
│   ├── Test_Execution_Template.md
│   └── ...
│
├── tests/                         # Test code
│   ├── javascript/
│   │   ├── apple-signin-test.js
│   │   └── performance-test.js
│   ├── ios/
│   │   └── AppleSignInTests.swift
│   └── android/
│       └── AppleSignInTest.kt
│
├── package.json                   # Dependencies
├── .gitignore                     # Git ignore rules
└── README.md                      # This file
```

## 📚 Documentation

### Main Guides:
- **[Testing_Guide_Apple_SignIn.md](docs/Testing_Guide_Apple_SignIn.md)** - Complete testing guide with code examples
- **[Quick_Testing_Checklist.md](docs/Quick_Testing_Checklist.md)** - Quick reference during testing

### Test Management:
- **[Airtable_Test_Case_Guide.md](docs/Airtable_Test_Case_Guide.md)** - How to use Airtable for test tracking
- **[How_to_Use_Test_Management_Tool.md](docs/How_to_Use_Test_Management_Tool.md)** - Test management tool guide

### Test Results:
- **[Video_Analysis_Notes.md](docs/Video_Analysis_Notes.md)** - Test execution notes and results

## 🧪 Test Cases

### Test Case: AO-11 - Apple Sign-In Authentication

**Feature Area:** Auth & Onboarding  
**Status:** See Airtable for current status

**Steps:**
1. Launch the application
2. Navigate to the login screen
3. Select "Continue with Apple" option
4. Complete Apple authentication flow
5. Choose to share or hide email address

**Expected Results:**
1. Apple Sign-In dialog appears with proper scopes (FULL_NAME, EMAIL)
2. AppleAuthentication.signInAsync() returns valid credential with identityToken
3. OAuthProvider credential created for 'apple.com'
4. User successfully authenticated and redirected to authenticated section
5. Auth state updated correctly
6. Apple provider data stored in authProviders

## 💻 Code Files

### JavaScript/React Native:
- `tests/javascript/apple-signin-test.js` - 12 comprehensive test cases
- `tests/javascript/performance-test.js` - Performance benchmarking

### iOS (Swift):
- `tests/ios/AppleSignInTests.swift` - 12 XCTest cases

### Android (Kotlin):
- `tests/android/AppleSignInTest.kt` - 13 Espresso test cases

## 📊 Performance Targets

| Metric | Target | Test File |
|--------|--------|-----------|
| App Launch | < 3 sec | performance-test.js |
| Login Screen | < 2 sec | performance-test.js |
| Apple Dialog | < 2 sec | performance-test.js |
| Authentication | < 3 sec | performance-test.js |
| Total Flow | < 10 sec | performance-test.js |

## 🛠️ Setup

### Prerequisites:
- Node.js (for JavaScript tests)
- Xcode (for iOS tests)
- Android Studio (for Android tests)
- Test device with app installed

### Install Dependencies:
```bash
npm install
```

## 📝 Testing Workflow

1. **Prepare**: Set up test device and environment
2. **Test**: Follow manual testing guide or run automated tests
3. **Record**: Record test execution for documentation
4. **Document**: Update test results in test management tool
5. **Report**: Share findings with team

## 📖 Additional Resources

- [Jest Documentation](https://jestjs.io/)
- [React Native Testing Library](https://callstack.github.io/react-native-testing-library/)
- [XCTest Documentation](https://developer.apple.com/documentation/xctest)
- [Espresso Documentation](https://developer.android.com/training/testing/espresso)

## 🤝 Contributing

1. Follow the testing guides
2. Update test results in Airtable
3. Document any issues found
4. Keep test code up to date

## 📄 License

This is a private automation testing repository.

## 👤 Author

**Manasa**  
Test Documentation and Test Cases

---

**Last Updated:** December 2025
