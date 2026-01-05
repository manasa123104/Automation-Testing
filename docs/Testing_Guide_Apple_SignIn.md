# Complete Testing Guide: Apple Sign-In Authentication

## 📋 Table of Contents
1. [Prerequisites](#prerequisites)
2. [Setup Instructions](#setup-instructions)
3. [Manual Testing Steps](#manual-testing-steps)
4. [Automated Testing Code](#automated-testing-code)
5. [Using Spanr App for Recording](#using-spanr-app)
6. [What to Document](#what-to-document)
7. [Troubleshooting](#troubleshooting)

---

## 🔧 Prerequisites

### Required Tools & Accounts
- [ ] **Test Device**: iOS device (iPhone/iPad) OR Android device
- [ ] **Apple ID** for testing (use a test account, not your personal one)
- [ ] **Test App**: The app you're testing (installed and ready)
- [ ] **Screen Recording**: Built-in screen recorder or device recording feature
- [ ] **Network**: Stable Wi-Fi connection recommended

### For iOS Testing:
- iOS 13+ device
- Apple Developer account (if testing in development mode)
- Xcode (if doing code-level testing)

### For Android Testing:
- Android device with Google Play Services
- Android 5.0+ (API level 21+)

---

## ⚙️ Setup Instructions

### Step 1: Prepare Your Testing Environment

```bash
# 1. Ensure screen recording is set up
# - Enable screen recording on your device
# - Verify recording feature works
# - Check available storage space

# 2. Clear app data (if needed for fresh test)
# iOS: Delete and reinstall app
# Android: Settings > Apps > [App Name] > Clear Data
```

### Step 2: Prepare Test Apple ID
- Create or use a test Apple ID
- Ensure it's not your personal account
- Note down credentials for testing

### Step 3: Enable Screen Recording
- **iOS**: Settings > Control Center > Add Screen Recording
- **Android**: Use built-in screen recorder or device recording feature

---

## 📱 Manual Testing Steps

### Test Case: Apple Sign-In Authentication

#### **Step 1: Launch the Application**
```
Action: Open the app on your device
Expected: App launches successfully
Time: Start timer
```

**What to Record:**
- App launch time
- Initial screen appearance
- Any loading indicators

#### **Step 2: Navigate to Login Screen**
```
Action: Find and tap on login/sign-in option
Expected: Login screen appears
Time: Note the time taken
```

**What to Check:**
- [ ] Login screen loads within 2-3 seconds
- [ ] "Continue with Apple" button is visible
- [ ] Button is properly positioned and clickable
- [ ] UI elements are not cut off or overlapping

#### **Step 3: Select "Continue with Apple"**
```
Action: Tap the "Continue with Apple" button
Expected: Apple Sign-In dialog appears
Time: Note response time
```

**What to Observe:**
- [ ] Apple Sign-In dialog appears
- [ ] Dialog shows proper scopes (FULL_NAME, EMAIL)
- [ ] Dialog appears within 1-2 seconds
- [ ] Dialog is properly formatted

#### **Step 4: Complete Apple Authentication Flow**
```
Action: 
  a) Authenticate with Face ID/Touch ID/Password
  b) Review the information Apple will share
Expected: Authentication completes
Time: Note authentication time
```

**What to Check:**
- [ ] Face ID/Touch ID prompt appears (if enabled)
- [ ] Authentication succeeds
- [ ] No errors during authentication
- [ ] Email sharing option is presented

#### **Step 5: Choose Email Sharing Option**
```
Action: Choose to "Share My Email" or "Hide My Email"
Expected: Selection is processed
Time: Note processing time
```

**What to Verify:**
- [ ] Option selection works
- [ ] App processes the selection
- [ ] No errors occur

#### **Step 6: Verify Successful Authentication**
```
Action: Wait for app to process authentication
Expected: User is redirected to authenticated section
Time: Total time from button tap to home screen
```

**What to Confirm:**
- [ ] User is successfully authenticated
- [ ] Redirected to authenticated/home section
- [ ] Auth state is updated correctly
- [ ] User profile/data is loaded

---

## 💻 Automated Testing Code

### For React Native / Expo Apps

```javascript
// test/apple-signin.test.js
import { render, fireEvent, waitFor } from '@testing-library/react-native';
import * as AppleAuthentication from 'expo-apple-authentication';
import LoginScreen from '../screens/LoginScreen';

describe('Apple Sign-In Authentication', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  test('should display Apple Sign-In button', () => {
    const { getByText } = render(<LoginScreen />);
    const appleButton = getByText('Continue with Apple');
    expect(appleButton).toBeTruthy();
  });

  test('should call AppleAuthentication.signInAsync when button is pressed', async () => {
    const mockCredential = {
      user: 'user123',
      identityToken: 'mock-token',
      email: 'test@privaterelay.appleid.com',
      fullName: {
        givenName: 'Test',
        familyName: 'User'
      }
    };

    jest.spyOn(AppleAuthentication, 'signInAsync')
      .mockResolvedValue(mockCredential);

    const { getByText } = render(<LoginScreen />);
    const appleButton = getByText('Continue with Apple');
    
    fireEvent.press(appleButton);

    await waitFor(() => {
      expect(AppleAuthentication.signInAsync).toHaveBeenCalledWith({
        requestedScopes: [
          AppleAuthentication.AppleAuthenticationScope.FULL_NAME,
          AppleAuthentication.AppleAuthenticationScope.EMAIL,
        ],
      });
    });
  });

  test('should create OAuthProvider credential for apple.com', async () => {
    const mockCredential = {
      identityToken: 'mock-identity-token',
      user: 'user123'
    };

    // Mock Firebase Auth or your auth provider
    const mockOAuthCredential = {
      providerId: 'apple.com',
      idToken: mockCredential.identityToken
    };

    // Your authentication logic here
    expect(mockOAuthCredential.providerId).toBe('apple.com');
  });

  test('should handle authentication errors', async () => {
    jest.spyOn(AppleAuthentication, 'signInAsync')
      .mockRejectedValue(new Error('User cancelled'));

    const { getByText } = render(<LoginScreen />);
    const appleButton = getByText('Continue with Apple');
    
    fireEvent.press(appleButton);

    await waitFor(() => {
      // Verify error handling
      expect(getByText(/error|failed/i)).toBeTruthy();
    });
  });
});
```

### For Native iOS (Swift)

```swift
// AppleSignInTests.swift
import XCTest
import AuthenticationServices
@testable import YourApp

class AppleSignInTests: XCTestCase {
    
    var loginViewController: LoginViewController!
    
    override func setUp() {
        super.setUp()
        loginViewController = LoginViewController()
    }
    
    func testAppleSignInButtonExists() {
        // Verify button exists
        let appleButton = loginViewController.appleSignInButton
        XCTAssertNotNil(appleButton, "Apple Sign-In button should exist")
    }
    
    func testAppleSignInFlow() {
        // Mock ASAuthorizationAppleIDProvider
        let provider = ASAuthorizationAppleIDProvider()
        let request = provider.createRequest()
        request.requestedScopes = [.fullName, .email]
        
        XCTAssertEqual(request.requestedScopes?.count, 2, 
                      "Should request FULL_NAME and EMAIL scopes")
    }
    
    func testHandleAuthorizationSuccess() {
        // Mock successful authorization
        let mockCredential = MockAppleIDCredential(
            identityToken: Data("mock-token".utf8),
            user: "user123",
            email: "test@example.com"
        )
        
        // Test your auth handler
        loginViewController.handleAuthorization(mockCredential)
        
        // Verify auth state updated
        XCTAssertTrue(loginViewController.isAuthenticated)
    }
}
```

### For Android (Kotlin)

```kotlin
// AppleSignInTest.kt
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class AppleSignInTest {
    
    @Test
    fun testAppleSignInButtonVisible() {
        // Verify button is visible
        val activity = ActivityTestRule(LoginActivity::class.java)
        onView(withId(R.id.apple_sign_in_button))
            .check(matches(isDisplayed()))
    }
    
    @Test
    fun testAppleSignInFlow() {
        // Test sign-in flow
        onView(withId(R.id.apple_sign_in_button))
            .perform(click())
        
        // Verify Apple Sign-In dialog appears
        // Note: Actual Apple Sign-In requires device/emulator setup
    }
}
```

### Performance Testing Code

```javascript
// performance/apple-signin-performance.test.js
describe('Apple Sign-In Performance', () => {
  test('should complete authentication within 5 seconds', async () => {
    const startTime = Date.now();
    
    // Trigger Apple Sign-In
    await triggerAppleSignIn();
    
    const endTime = Date.now();
    const duration = endTime - startTime;
    
    expect(duration).toBeLessThan(5000); // 5 seconds
  });

  test('should load login screen within 2 seconds', async () => {
    const startTime = Date.now();
    
    await navigateToLogin();
    
    const endTime = Date.now();
    const duration = endTime - startTime;
    
    expect(duration).toBeLessThan(2000); // 2 seconds
  });
});
```

---

## 📹 Using Screen Recording

### Step-by-Step Recording Guide

1. **Start Screen Recording**
   ```
   - Open screen recording on your device
   - Ensure recording feature is enabled
   - Check that you have sufficient storage
   ```

2. **Start Recording**
   ```
   - Tap "Start Recording" or use device screen recorder
   - Verify recording indicator is showing
   - Note the start time
   ```

3. **Perform Test Steps**
   ```
   - Follow the manual testing steps above
   - Perform all actions naturally
   - Don't rush - let animations complete
   ```

4. **Stop Recording**
   ```
   - After completing all test steps
   - Stop the screen recording
   - Save the recording with a descriptive name
   ```

5. **Review Recording**
   ```
   - Watch the recording
   - Note timestamps for each action
   - Document any issues or observations
   ```

### What to Capture in Recording:
- ✅ App launch time
- ✅ Login screen load time
- ✅ Apple Sign-In button tap
- ✅ Apple dialog appearance time
- ✅ Authentication completion time
- ✅ Home screen load time
- ✅ Any error messages or delays

---

## 📝 What to Document

### Create a Test Execution Log

```markdown
## Test Execution Log - Apple Sign-In

**Date:** [Date]
**Tester:** Manasa
**Device:** [Device Model, OS Version]
**App Version:** [Version Number]
**Network:** [Wi-Fi / Cellular]

### Test Results:

#### Test 1: Happy Flow - Successful Sign-In
- **Start Time:** [Timestamp]
- **Button Tap Time:** [Timestamp]
- **Dialog Appear Time:** [Timestamp]
- **Auth Complete Time:** [Timestamp]
- **Home Screen Load Time:** [Timestamp]
- **Total Time:** [Calculate]
- **Result:** ✅ PASS / ❌ FAIL
- **Notes:** [Any observations]

#### Test 2: User Cancels Authentication
- **Start Time:** [Timestamp]
- **Cancel Action Time:** [Timestamp]
- **App Behavior:** [What happened]
- **Result:** ✅ PASS / ❌ FAIL
- **Notes:** [Any observations]

#### Test 3: Network Issues
- **Network Condition:** [Slow / Offline / Unstable]
- **Behavior:** [What happened]
- **Error Messages:** [If any]
- **Result:** ✅ PASS / ❌ FAIL
- **Notes:** [Any observations]
```

### Update Your Notes File

After testing, update `Video_Analysis_Notes.md`:

```markdown
### Test Status
- [x] Not Tested
- [ ] In Progress
- [ ] Pass
- [ ] Fail

### Notes:
- Tested on: [Device]
- Date: [Date]
- Results: [Summary]
- Issues Found: [List any issues]
- Performance: [Timing data]
```

---

## 🔍 Troubleshooting

### Common Issues and Solutions

#### Issue 1: Apple Sign-In Button Not Appearing
```
Possible Causes:
- App not configured for Apple Sign-In
- iOS version too old (< iOS 13)
- Missing entitlements

Solutions:
- Check app configuration
- Verify iOS version
- Review app entitlements in Xcode
```

#### Issue 2: Authentication Fails
```
Possible Causes:
- Invalid Apple ID
- Network issues
- App configuration error

Solutions:
- Verify Apple ID credentials
- Check network connection
- Review app logs for errors
```

#### Issue 3: Dialog Doesn't Appear
```
Possible Causes:
- User already signed in
- App state issue
- System-level problem

Solutions:
- Sign out and try again
- Restart app
- Check system settings
```

#### Issue 4: Slow Performance
```
Possible Causes:
- Network latency
- Device performance
- App optimization issues

Solutions:
- Test on stable Wi-Fi
- Test on different devices
- Check app performance metrics
```

---

## ✅ Testing Checklist

Use this checklist during testing:

- [ ] App launches successfully
- [ ] Login screen loads within acceptable time
- [ ] "Continue with Apple" button is visible and clickable
- [ ] Apple Sign-In dialog appears with correct scopes
- [ ] Authentication completes successfully
- [ ] Email sharing option works correctly
- [ ] User is redirected to authenticated section
- [ ] Auth state is updated correctly
- [ ] No errors or crashes occur
- [ ] Performance is acceptable (< 5 seconds total)
- [ ] Error handling works (test cancel scenario)
- [ ] Recording captured all steps

---

## 🎯 Next Steps After Testing

1. **Document Results**
   - Update `Video_Analysis_Notes.md`
   - Fill in test status and notes
   - Add performance metrics

2. **Create Report**
   - Summarize findings
   - Include screenshots/video
   - Note any bugs or issues

3. **Share with Team**
   - Send report to your uncle/team
   - Include video recording
   - Highlight any critical issues

4. **Follow Up**
   - Address any bugs found
   - Retest after fixes
   - Continue with next test cases

---

## 📞 Quick Reference

### Key Metrics to Track:
- **App Launch Time:** < 3 seconds
- **Login Screen Load:** < 2 seconds
- **Apple Dialog Appear:** < 2 seconds
- **Authentication Complete:** < 3 seconds
- **Total Flow Time:** < 10 seconds

### Important Files:
- `Video_Analysis_Notes.md` - Main test notes
- `Video_Analysis_Notes.docx` - Word version
- `Testing_Guide_Apple_SignIn.md` - This guide
- Video recordings from Spanr app

---

**Good luck with your testing! 🚀**


