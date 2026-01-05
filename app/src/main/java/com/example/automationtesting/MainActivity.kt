package com.example.automationtesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.automationtesting.ui.theme.AutomationtestingTheme

// --- Simulated Authentication Service ---
object AuthService {
    enum class AuthState { SIGNED_OUT, SIGNED_IN, SCHEDULED_FOR_DELETION }
    var currentAuthState by mutableStateOf(AuthState.SIGNED_OUT)

    // Simulate a user who has scheduled their account for deletion
    fun loginAsUserScheduledForDeletion() {
        currentAuthState = AuthState.SCHEDULED_FOR_DELETION
    }
    
    fun loginAsNormalUser() {
        currentAuthState = AuthState.SIGNED_IN
    }

    fun reactivateAccount() {
        currentAuthState = AuthState.SIGNED_IN
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutomationtestingTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    // The UI now changes based on the user's authentication state
    when (AuthService.currentAuthState) {
        AuthService.AuthState.SIGNED_OUT -> SocialSignInScreen()
        AuthService.AuthState.SIGNED_IN -> AuthenticatedScreen()
        AuthService.AuthState.SCHEDULED_FOR_DELETION -> ReactivateUserAccountScreen()
    }
}

@Composable
fun SocialSignInScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // In a real app, you would have different logic for Apple vs Google.
        Button(onClick = { AuthService.loginAsNormalUser() }) {
            Text("Continue with Apple")
        }
        Spacer(modifier = Modifier.height(8.dp))
        // For this test case, we'll use the Google button to simulate the deletion flow.
        Button(onClick = { AuthService.loginAsUserScheduledForDeletion() }) {
            Text("Continue with Google (as deletion user)")
        }
    }
}

@Composable
fun AuthenticatedScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome, User!", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun ReactivateUserAccountScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.LightGray).padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Account Scheduled for Deletion", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Your account is scheduled for deletion. To continue using our service, please reactivate your account.")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { AuthService.reactivateAccount() }) {
            Text("Reactivate Account")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReactivateScreen() {
    AutomationtestingTheme {
        ReactivateUserAccountScreen()
    }
}