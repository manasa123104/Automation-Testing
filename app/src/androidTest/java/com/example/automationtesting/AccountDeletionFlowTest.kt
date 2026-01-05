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
 * Test case for the "Account Scheduled for Deletion" flow (AO-13).
 */
@RunWith(AndroidJUnit4::class)
class AccountDeletionFlowTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun accountScheduledForDeletion_reactivationFlow() {
        // Step 1: Launch application & Complete authentication flow (as deletion user)
        composeTestRule.onNodeWithText("Continue with Google (as deletion user)").performClick()

        // Step 2 & 3: Observe post-authentication routing and see reactivation screen
        // Expected Result 4: User sees account reactivation screen
        composeTestRule.onNodeWithText("Account Scheduled for Deletion").assertIsDisplayed()

        // Step 4: Reactivate the account
        composeTestRule.onNodeWithText("Reactivate Account").performClick()

        // Expected Result 6: User remains in reactivation flow until account is reactivated.
        // Now that it's reactivated, they should be in the normal authenticated section.
        composeTestRule.onNodeWithText("Welcome, User!").assertIsDisplayed()
    }
}