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
class FreshAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testButtonClick_displaysMessage() {
        // Find the button and click it
        composeTestRule.onNodeWithText("Click Me!").performClick()

        // Verify that the success message is displayed
        composeTestRule.onNodeWithText("It Works!").assertIsDisplayed()
    }
}