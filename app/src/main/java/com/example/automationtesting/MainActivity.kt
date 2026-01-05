package com.example.automationtesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.automationtesting.ui.theme.AutomationtestingTheme

// --- Simulated Authentication Service ---
// This object simulates the process of handling Apple Sign-In.
object AuthService {
    var currentAuthState: String = "SIGNED_OUT"
    var appleProviderData: Map<String, Any>? = null

    // This simulates the AppleAuthentication.signInAsync() call
    private fun signInAsync(scopes: List<String>): Map<String, Any> {
        // In a real app, this would return a real identityToken from Apple.
        // We simulate that the dialog appeared with the correct scopes.
        if (scopes.contains("FULL_NAME") && scopes.contains("EMAIL")) {
            return mapOf("identityToken" to "mock_identity_token_12345")
        }
        throw IllegalStateException(" signInAsync called with incorrect scopes")
    }

    // This simulates creating an OAuthProvider credential
    private fun createOAuthProviderCredential(identityToken: String): Boolean {
        // We simulate that the credential was created for 'apple.com'.
        return identityToken.isNotEmpty()
    }

    // This function simulates the entire authentication flow from the test case.
    fun continueWithApple() {
        // 1. Apple Sign-In dialog appears with proper scopes (FULL_NAME, EMAIL)
        val appleCredential = signInAsync(listOf("FULL_NAME", "EMAIL"))
        val identityToken = appleCredential["identityToken"] as String

        // 2. AppleAuthentication.signInAsync() returns valid credential
        if (identityToken.isNotEmpty()) {
            // 3. OAuthProvider credential created for 'apple.com'
            val oAuthProviderCreated = createOAuthProviderCredential(identityToken)
            if (oAuthProviderCreated) {
                // 4. User successfully authenticated
                // 5. Auth state updated correctly
                currentAuthState = "SIGNED_IN"

                // 6. Apple provider data stored
                appleProviderData = mapOf("providerId" to "apple.com")
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutomationtestingTheme {
                SocialSignInScreen()
            }
        }
    }
}

@Composable
fun SocialSignInScreen() {
    var authState by remember { mutableStateOf(AuthService.currentAuthState) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (authState == "SIGNED_IN") {
            Text("Welcome, User!", style = MaterialTheme.typography.headlineMedium)
        } else {
            Button(onClick = { 
                AuthService.continueWithApple()
                authState = AuthService.currentAuthState 
            }) {
                Text("Continue with Apple")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { 
                // For now, Google login will also use the same auth logic
                AuthService.continueWithApple() 
                authState = AuthService.currentAuthState
            }) {
                Text("Continue with Google")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AutomationtestingTheme {
        SocialSignInScreen()
    }
}