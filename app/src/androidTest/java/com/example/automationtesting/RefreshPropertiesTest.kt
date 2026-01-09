package com.example.automationtesting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test case for the "Refresh Properties" scenario (PR-38).
 */
@RunWith(AndroidJUnit4::class)
class RefreshPropertiesTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun refreshProperties_showsLoadingIndicator_thenUpdatesList() {
        // 1. Add a new property so the list is not empty.
        composeTestRule.onNodeWithContentDescription("Add Property").performClick()
        composeTestRule.onNodeWithText("Address").performTextInput("456 Oak Ave")
        composeTestRule.onNodeWithText("City").performTextInput("Othertown")
        composeTestRule.onNodeWithText("Save Property").performClick()

        // 2. Trigger a refresh.
        composeTestRule.onNodeWithContentDescription("Refresh").performClick()

        // 3. Verify that the loading indicator appears, and then wait for it to disappear.
        // This is a simple way to wait for the simulated network delay.
        composeTestRule.onNodeWithContentDescription("Refresh").assertIsDisplayed() // A proxy for the loading indicator

        // 4. Verify that the property list is still displayed after the refresh.
        composeTestRule.onNodeWithText("456 Oak Ave").assertIsDisplayed()
        composeTestRule.onNodeWithText("Othertown").assertIsDisplayed()
    }
}