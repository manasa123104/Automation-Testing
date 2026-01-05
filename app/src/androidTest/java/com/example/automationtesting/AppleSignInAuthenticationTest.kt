package com.example.automationtesting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppleSignInAuthenticationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun appleSignIn_happyPath() {
        // 3. Select "Continue with Apple" option
        composeTestRule.onNodeWithText("Continue with Apple").performClick()

        // 4. User successfully authenticated and redirected to authenticated section
        composeTestRule.onNodeWithText("Welcome, User!").assertIsDisplayed()
        
        // 5. Auth state updated correctly
        assertEquals("SIGNED_IN", AuthService.currentAuthState)

        // 6. Apple provider data stored in authProviders
        assertEquals("apple.com", AuthService.appleProviderData?.get("providerId"))
    }
}