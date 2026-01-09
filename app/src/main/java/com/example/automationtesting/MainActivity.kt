package com.example.automationtesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.automationtesting.ui.theme.AutomationtestingTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// --- Data Models ---
data class Property(
    val id: String,
    val name: String,
    val address: String,
    val city: String,
    val isPrimary: Boolean,
    var isActive: Boolean = false,
    val imageUrl: String? = null // For PR-41: Fallback logic
)

// --- Simulated Global State ---
object AppState {
    var isLoggedIn by mutableStateOf(false)
    var isAccountScheduledForDeletion by mutableStateOf(false)
    val savedProperties = mutableStateListOf<Property>()
    
    // Derived sorted list for PR-39
    val sortedProperties: List<Property>
        get() = savedProperties.sortedWith(
            compareByDescending<Property> { it.isActive }
                .thenByDescending { it.isPrimary }
                .thenBy { it.name }
        )

    fun reset() {
        isLoggedIn = false
        isAccountScheduledForDeletion = false
        savedProperties.clear()
    }
    
    fun setActive(propertyId: String) {
        savedProperties.forEachIndexed { index, prop ->
            savedProperties[index] = prop.copy(isActive = prop.id == propertyId)
        }
    }

    fun togglePrimary(propertyId: String) {
        savedProperties.forEachIndexed { index, prop ->
            if (prop.id == propertyId) {
                savedProperties[index] = prop.copy(isPrimary = !prop.isPrimary)
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutomationtestingTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val startDestination = if (!AppState.isLoggedIn) "login" else "dashboard"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") { LoginScreen(navController) }
        composable("reactivate") { ReactivateAccountScreen(navController) }
        composable("dashboard") { DashboardScreen(navController) }
        composable("add_property") { AddPropertyScreen(navController) }
    }
}

// --- AO-11: Login Screen ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F5F0))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Spanr", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFFFF6B6B))
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = { 
                AppState.isLoggedIn = true
                AppState.isAccountScheduledForDeletion = false
                navController.navigate("dashboard") { popUpTo("login") { inclusive = true } }
            },
            modifier = Modifier.fillMaxWidth().height(56.dp).testTag("apple_login_button"),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            // Replaced Icons.Default.Apple with AccountCircle as it's not a standard Material Icon
            Icon(Icons.Default.AccountCircle, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Continue with Apple")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedButton(
            onClick = { 
                // AO-13: Simulate login with an account scheduled for deletion
                AppState.isLoggedIn = true
                AppState.isAccountScheduledForDeletion = true
                navController.navigate("reactivate") { popUpTo("login") { inclusive = true } }
            },
            modifier = Modifier.fillMaxWidth().height(56.dp).testTag("google_login_deletion_button")
        ) {
            Icon(Icons.Default.AccountCircle, contentDescription = null) 
            Spacer(Modifier.width(8.dp))
            Text("Continue with Google (Simulate Deletion Flow)")
        }
    }
}

// --- AO-13: Account Scheduled for Deletion Screen ---
@Composable
fun ReactivateAccountScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.Warning, contentDescription = null, modifier = Modifier.size(64.dp), tint = Color.DarkGray)
        Spacer(modifier = Modifier.height(24.dp))
        Text("Account Scheduled for Deletion", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Your account is currently scheduled for deletion. You must reactivate it to continue using the app.", textAlign = TextAlign.Center, color = Color.DarkGray)
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                AppState.isAccountScheduledForDeletion = false
                navController.navigate("dashboard") { popUpTo("reactivate") { inclusive = true } }
            },
            modifier = Modifier.fillMaxWidth().height(56.dp).testTag("reactivate_button"),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B6B))
        ) {
            Text("Reactivate User Account")
        }
    }
}

// --- Dashboard Screen ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    var isLoading by remember { mutableStateOf(false) }
    var isHorizontalLayout by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Properties") },
                actions = {
                    IconButton(onClick = { isHorizontalLayout = !isHorizontalLayout }) {
                        Icon(if (isHorizontalLayout) Icons.Default.ViewList else Icons.Default.ViewStream, contentDescription = "Toggle Layout")
                    }
                    IconButton(
                        onClick = { 
                            scope.launch {
                                isLoading = true
                                delay(1000)
                                isLoading = false
                            }
                        },
                        modifier = Modifier.testTag("refresh_button")
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_property") },
                containerColor = Color(0xFFFF6B6B),
                modifier = Modifier.testTag("add_property_fab")
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Property", tint = Color.White)
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFFF7F5F0))) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color(0xFFFF6B6B))
            } else if (AppState.savedProperties.isEmpty()) {
                Text("No properties found. Tap + to add one.", color = Color.Gray, modifier = Modifier.align(Alignment.Center))
            } else {
                if (isHorizontalLayout) {
                    LazyRow(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items(AppState.sortedProperties) { property ->
                            PropertyCard(
                                property = property, 
                                isHorizontal = true,
                                onSelect = { AppState.setActive(property.id) },
                                onTogglePrimary = { AppState.togglePrimary(property.id) }
                            )
                        }
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                        items(AppState.sortedProperties) { property ->
                            PropertyCard(
                                property = property, 
                                isHorizontal = false,
                                onSelect = { AppState.setActive(property.id) },
                                onTogglePrimary = { AppState.togglePrimary(property.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PropertyCard(property: Property, isHorizontal: Boolean, onSelect: () -> Unit, onTogglePrimary: () -> Unit) {
    Card(
        modifier = Modifier
            .then(if (isHorizontal) Modifier.width(280.dp) else Modifier.fillMaxWidth())
            .padding(vertical = 8.dp)
            .clickable { onSelect() },
        colors = CardDefaults.cardColors(
            containerColor = if (property.isActive) Color(0xFFFFF9C4) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (property.isActive) 8.dp else 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.BrokenImage, contentDescription = "Street View Not Available", modifier = Modifier.size(48.dp), tint = Color.Gray)
            }
            
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(property.name, fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.weight(1f))
                    if (property.isActive) {
                        Icon(Icons.Default.CheckCircle, contentDescription = "Active", tint = Color(0xFF2E7D32), modifier = Modifier.size(20.dp))
                    }
                }
                Text(property.address, color = Color.Gray, fontSize = 14.sp)
                Text(property.city, color = Color.Gray, fontSize = 14.sp)
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Switch(
                        checked = property.isPrimary,
                        onCheckedChange = { onTogglePrimary() }
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Primary", fontSize = 12.sp, color = if (property.isPrimary) Color(0xFF2E7D32) else Color.Gray)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPropertyScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var isPrimary by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Property") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp)) {
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Property Name") }, modifier = Modifier.fillMaxWidth().testTag("name_field"))
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") }, modifier = Modifier.fillMaxWidth().testTag("address_field"))
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = city, onValueChange = { city = it }, label = { Text("City") }, modifier = Modifier.fillMaxWidth().testTag("city_field"))
            Spacer(modifier = Modifier.height(24.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isPrimary, onCheckedChange = { isPrimary = it }, modifier = Modifier.testTag("primary_checkbox"))
                Text("Set as primary residence", fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = {
                    if (name.isNotBlank() && address.isNotBlank()) {
                        AppState.savedProperties.add(Property(java.util.UUID.randomUUID().toString(), name, address, city, isPrimary))
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp).testTag("save_property_button"),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B6B))
            ) {
                Text("Save Property")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    AutomationtestingTheme {
        AppNavigation()
    }
}
