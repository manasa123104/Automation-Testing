package com.example.automationtesting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GoogleSignInTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun googleSignIn_happyPath() {
        // Click the "Continue with Google" button
        composeTestRule.onNodeWithText("Continue with Google").performClick()

        // Verify that the user is redirected to the authenticated section
        composeTestRule.onNodeWithText("Welcome, User!").assertIsDisplayed()
    }
}