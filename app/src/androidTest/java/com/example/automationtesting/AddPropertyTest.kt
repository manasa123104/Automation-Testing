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
 * Test case for the "Add Property" screen (PR-32).
 */
@RunWith(AndroidJUnit4::class)
class AddPropertyTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun addProperty_setAsPrimary_displaysOnMainScreen() {
        // 1. Open the Add Property screen.
        composeTestRule.onNodeWithContentDescription("Add Property").performClick()

        // 2. Fill in the required property details.
        composeTestRule.onNodeWithText("Address").performTextInput("123 Main St")
        composeTestRule.onNodeWithText("City").performTextInput("Anytown")

        // 3. Select the "Set as primary residence" checkbox.
        composeTestRule.onNodeWithText("Set as primary residence").performClick()

        // 4. Save the property.
        composeTestRule.onNodeWithText("Save Property").performClick()

        // Expected Result: The property should be marked as the primary residence after saving.
        // We verify this by checking that the new property details are displayed on the main screen.
        composeTestRule.onNodeWithText("123 Main St").assertIsDisplayed()
        composeTestRule.onNodeWithText("Anytown").assertIsDisplayed()
        composeTestRule.onNodeWithText("(Primary Residence)").assertIsDisplayed()
    }
}