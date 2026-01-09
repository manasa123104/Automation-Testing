package com.example.automationtesting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

/**
 * A collection of black box test cases for the Spanr app.
 * These tests are written without any knowledge of the app's internal source code,
 * relying only on what is visible on the screen.
 */
@RunWith(AndroidJUnit4::class)
class SpanrBlackBoxTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    /**
     * Test Case 1: Verify that the main login screen loads correctly.
     * Steps:
     * 1. Launch the application.
     * Expected Result:
     * - The main social login buttons are visible.
     */
    @Test
    fun testLoginScreen_isDisplayed() {
        // Verify that a key element of the login screen is visible
        composeTestRule.onNodeWithText("Continue with Apple").assertIsDisplayed()
        composeTestRule.onNodeWithText("Continue with Google").assertIsDisplayed()
    }
}