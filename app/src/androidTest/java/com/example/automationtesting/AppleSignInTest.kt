package com.example.automationtesting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test case for the "Apple Sign-In Authentication" scenario (AO-11).
 */
@RunWith(AndroidJUnit4::class)
class AppleSignInTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun appleSignIn_happyPath() {
        // 3. Select "Continue with Apple" option
        composeTestRule.onNodeWithText("Continue with Apple").performClick()

        // 4. User successfully authenticated and redirected to authenticated section
        // We verify this by checking for the "Welcome, User!" text.
        composeTestRule.onNodeWithText("Welcome, User!").assertIsDisplayed()
    }
}