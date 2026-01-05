# 🤖 Running Tests in Android Studio - Complete Guide

This guide provides step-by-step instructions for running Android automation tests in Android Studio.

---

## 📋 Table of Contents

1. [Prerequisites](#prerequisites)
2. [Setting Up Your Android Project](#setting-up-your-android-project)
3. [Adding Test Files to Android Studio](#adding-test-files-to-android-studio)
4. [Running Tests in Android Studio](#running-tests-in-android-studio)
5. [Viewing Test Results](#viewing-test-results)
6. [Running Specific Tests](#running-specific-tests)
7. [Debugging Tests](#debugging-tests)
8. [Troubleshooting](#troubleshooting)

---

## 🔧 Prerequisites

Before you begin, ensure you have:

- ✅ **Android Studio** installed (latest version recommended)
- ✅ **Android SDK** configured
- ✅ **Android Emulator** or **Physical Device** set up
- ✅ **Gradle** synced in your project
- ✅ An existing Android project (or create a new one)

---

## 📦 Setting Up Your Android Project

### Step 1: Open Your Android Project

1. Launch **Android Studio**
2. Click **File** → **Open**
3. Navigate to your Android project folder
4. Click **OK**

### Step 2: Verify Project Structure

Your Android project should have this structure:

```
YourApp/
├── app/
│   ├── src/
│   │   ├── main/              # Main app code
│   │   │   └── java/com/yourapp/
│   │   └── androidTest/       # Instrumented tests (what we need!)
│   │       └── java/com/yourapp/
│   └── build.gradle
├── build.gradle
└── settings.gradle
```

**Important:** Tests go in `androidTest/`, NOT `test/` folder!

### Step 3: Check build.gradle Dependencies

Open `app/build.gradle` and ensure you have these dependencies:

```gradle
dependencies {
    // Android Testing Support Library
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
    androidTestImplementation 'androidx.test:runner:1.5.2'
    androidTestImplementation 'androidx.test:rules:1.5.2'
    
    // Your app dependencies
    implementation 'androidx.appcompat:appcompat:1.6.1'
    // ... other dependencies
}
```

---

## 📁 Adding Test Files to Android Studio

### Step 1: Locate the Test File

1. In this repository, navigate to: `tests/android/AppleSignInTest.kt`
2. Open the file and **copy its contents**

### Step 2: Create Test Directory in Android Studio

1. In Android Studio, open the **Project** view (left sidebar)
2. Navigate to: `app/src/androidTest/java/`
3. Right-click on the `java` folder
4. Select **New** → **Package**
5. Enter your app's package name (e.g., `com.yourapp.tests`)
6. Click **OK**

### Step 3: Create the Test File

1. Right-click on the package you just created
2. Select **New** → **Kotlin Class/File**
3. Name it: `AppleSignInTest`
4. Select **Class** and click **OK**

### Step 4: Paste and Customize the Test Code

1. **Paste** the test code from `AppleSignInTest.kt`
2. **Update the package name** to match your app:

```kotlin
package com.yourapp.tests  // Change this to your package name
```

3. **Update the Activity reference**:

```kotlin
@get:Rule
val activityRule = ActivityTestRule(LoginActivity::class.java)
// Change LoginActivity to your actual login activity
```

4. **Update resource IDs** to match your app:

```kotlin
onView(withId(R.id.apple_sign_in_button))  // Use your actual button ID
```

### Step 5: Sync Gradle

1. Click **File** → **Sync Project with Gradle Files**
2. Wait for sync to complete

---

## 🚀 Running Tests in Android Studio

### Method 1: Run All Tests in a Class

1. **Open** `AppleSignInTest.kt` in the editor
2. **Right-click** anywhere in the test class
3. Select **Run 'AppleSignInTest'**

   **OR**

4. Click the **green play button** (▶️) next to the class name
5. Select **Run 'AppleSignInTest'**

### Method 2: Run a Single Test Method

1. **Open** `AppleSignInTest.kt`
2. **Right-click** on a specific test method (e.g., `testAppleSignInButtonVisible`)
3. Select **Run 'testAppleSignInButtonVisible()'`

   **OR**

4. Click the **green play button** (▶️) next to the test method name

### Method 3: Run Tests from Project View

1. In the **Project** view, navigate to your test file
2. **Right-click** on `AppleSignInTest.kt`
3. Select **Run 'AppleSignInTest'**

### Method 4: Using the Run Configuration

1. Click the **Run** menu → **Edit Configurations**
2. Click the **+** button → Select **Android Instrumented Tests**
3. Configure:
   - **Name**: `AppleSignInTest`
   - **Module**: `app`
   - **Test class**: `com.yourapp.tests.AppleSignInTest`
4. Click **OK**
5. Select the configuration from the dropdown
6. Click **Run** (▶️)

---

## 📊 Viewing Test Results

### Test Results Panel

After running tests, you'll see the **Run** panel at the bottom:

```
AppleSignInTest
  ✓ testAppleSignInButtonVisible (2.3s)
  ✓ testAppleSignInButtonText (1.8s)
  ✓ testAppleSignInButtonClickable (2.1s)
  ✓ testAppleSignInRequestScopes (2.5s)
  ✓ testCredentialReceived (2.0s)
  ✓ testOAuthProviderCredentialCreated (2.4s)
  ✓ testSuccessfulAuthentication (3.2s)
  ✓ testAuthStateUpdated (2.1s)
  ✓ testProviderDataStored (2.3s)
  ✓ testUserCancellationHandled (1.9s)
  ✓ testAuthenticationErrorHandled (2.0s)
  ✓ testEmailSharingOption (2.2s)
  ✓ testHideEmailOption (2.1s)

13 tests passed, 0 failed
Total execution time: 28.5 seconds
```

### Understanding the Results

- **Green checkmark (✓)**: Test passed
- **Red X (✗)**: Test failed
- **Gray circle (○)**: Test skipped
- **Time**: Execution time for each test

### Viewing Detailed Results

1. Click on a **failed test** to see error details
2. Check the **Logcat** tab for detailed logs
3. View **screenshots** (if configured) in the test results

---

## 🎯 Running Specific Tests

### Run Tests Matching a Pattern

1. Open **Run** → **Edit Configurations**
2. In **Test options**, enter:
   ```
   --tests "com.yourapp.tests.AppleSignInTest.testAppleSignInButtonVisible"
   ```
3. Click **OK** and run

### Run Tests by Tag

Add tags to your tests:

```kotlin
@Test
@Tag("smoke")
fun testAppleSignInButtonVisible() {
    // test code
}
```

Then run:
```bash
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=smoke
```

---

## 🐛 Debugging Tests

### Method 1: Debug Mode

1. **Right-click** on the test class or method
2. Select **Debug 'AppleSignInTest'**
3. Set **breakpoints** in your test code
4. Step through the code using debug controls

### Method 2: View Logs

1. Open **Logcat** tab (bottom panel)
2. Filter by your test package: `com.yourapp.tests`
3. Look for test execution logs

### Method 3: Take Screenshots on Failure

Add to your test:

```kotlin
@Test
fun testAppleSignInButtonVisible() {
    try {
        // Your test code
    } catch (e: Exception) {
        // Take screenshot
        val screenshot = Screenshot.capture()
        screenshot.name = "test_failure"
        screenshot.format = Bitmap.CompressFormat.PNG
        screenshot.process()
        throw e
    }
}
```

---

## ⚙️ Advanced Configuration

### Run Tests on Specific Device

1. **Run** → **Edit Configurations**
2. Under **General** tab:
   - **Target**: Select specific device/emulator
   - **Deploy**: Choose APK or app module

### Run Tests with Coverage

1. **Right-click** on test class
2. Select **Run 'AppleSignInTest' with Coverage**
3. View coverage report in the **Coverage** tab

### Run Tests in Parallel

Add to `app/build.gradle`:

```gradle
android {
    testOptions {
        execution 'ANDROIDX_TEST_ORCHESTRATOR'
    }
}

dependencies {
    androidTestUtil 'androidx.test:orchestrator:1.4.2'
}
```

---

## 🔧 Troubleshooting

### Issue: "Test class not found"

**Solution:**
1. Make sure test is in `androidTest/` folder, not `test/`
2. Check package name matches
3. Sync Gradle: **File** → **Sync Project with Gradle Files**

### Issue: "Activity not found"

**Solution:**
1. Update `ActivityTestRule` with correct activity class
2. Ensure activity is registered in `AndroidManifest.xml`
3. Check import statements

### Issue: "View not found" or "No views in hierarchy"

**Solution:**
1. Verify resource IDs match your layout files
2. Check that views are visible (not hidden)
3. Add wait time if views load asynchronously:

```kotlin
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.contrib.CountingIdlingResource

// Wait for view
onView(withId(R.id.apple_sign_in_button))
    .check(matches(isDisplayed()))
```

### Issue: Tests run but nothing happens

**Solution:**
1. Check **Logcat** for errors
2. Verify device/emulator is connected: **View** → **Tool Windows** → **Device Manager**
3. Check app is installed on device

### Issue: Gradle sync fails

**Solution:**
1. **File** → **Invalidate Caches / Restart**
2. Clean project: **Build** → **Clean Project**
3. Rebuild: **Build** → **Rebuild Project**

### Issue: "Instrumentation run failed"

**Solution:**
1. Uninstall app from device: `adb uninstall com.yourapp`
2. Rebuild and reinstall
3. Run tests again

---

## 📝 Quick Reference Commands

### Using Android Studio UI:
- **Run all tests**: Right-click class → **Run 'AppleSignInTest'**
- **Run single test**: Right-click method → **Run 'testMethod()'**
- **Debug test**: Right-click → **Debug 'AppleSignInTest'**

### Using Terminal in Android Studio:
1. Open **Terminal** tab (bottom panel)
2. Run:

```bash
# Run all instrumented tests
./gradlew connectedAndroidTest

# Run specific test class
./gradlew connectedAndroidTest --tests "com.yourapp.tests.AppleSignInTest"

# Run specific test method
./gradlew connectedAndroidTest --tests "com.yourapp.tests.AppleSignInTest.testAppleSignInButtonVisible"

# Run with coverage
./gradlew connectedAndroidTest -Pandroid.testCoverage=true
```

---

## 🎓 Best Practices

1. **Organize Tests**: Group related tests in the same class
2. **Use Descriptive Names**: Test names should describe what they test
3. **Keep Tests Independent**: Each test should run independently
4. **Clean Up**: Use `@After` methods to clean up test state
5. **Use Test Rules**: Leverage `ActivityTestRule` for activity testing
6. **Mock External Dependencies**: Mock network calls, databases, etc.

---

## 📚 Additional Resources

- [Android Testing Documentation](https://developer.android.com/training/testing)
- [Espresso Testing Guide](https://developer.android.com/training/testing/espresso)
- [Android Studio User Guide](https://developer.android.com/studio/intro)

---

## ✅ Checklist

Before running tests, ensure:

- [ ] Android Studio is installed and updated
- [ ] Android project is open
- [ ] Test file is in `androidTest/` folder
- [ ] Package name matches your app
- [ ] Dependencies are added to `build.gradle`
- [ ] Gradle is synced
- [ ] Device/emulator is connected
- [ ] App is installed on device

---

**Happy Testing! 🎉**

For more information, see:
- [`HOW_TO_RUN_TESTS.md`](HOW_TO_RUN_TESTS.md) - General testing guide
- [`DEMO_EXAMPLE_OUTPUT.md`](DEMO_EXAMPLE_OUTPUT.md) - Expected outputs

