package com.example.automationtesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.automationtesting.ui.theme.AutomationtestingTheme

// --- Data Model ---
data class Property(val address: String, val city: String, val isPrimary: Boolean)

// A simple in-memory list to hold our saved properties
val savedProperties = mutableStateListOf<Property>()

// --- Main App ---
@OptIn(ExperimentalMaterial3Api::class)
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
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController = navController) }
        composable("add_property") { AddPropertyScreen(navController = navController) }
    }
}

// --- Screens ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("My Properties") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_property") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Property")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it).padding(16.dp)) {
            if (savedProperties.isEmpty()) {
                item {
                    Text("No properties saved yet. Click the '+' button to add one.")
                }
            }
            items(savedProperties) { property ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Text(property.address, fontWeight = FontWeight.Bold)
                        Text(property.city)
                        if (property.isPrimary) {
                            Text("(Primary Residence)", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPropertyScreen(navController: NavController) {
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var isPrimary by remember { mutableStateOf(false) }

    Scaffold(topBar = { TopAppBar(title = { Text("Add Property") }) }) {
        Column(modifier = Modifier.padding(it).padding(16.dp)) {
            OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = city, onValueChange = { city = it }, label = { Text("City") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isPrimary, onCheckedChange = { isPrimary = it })
                Spacer(Modifier.width(8.dp))
                Text("Set as primary residence")
            }
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = {
                    // Create a new property and add it to our list
                    val newProperty = Property(address, city, isPrimary)
                    savedProperties.add(newProperty)
                    // Navigate back to the main screen
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Property")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddPropertyScreen() {
    AutomationtestingTheme {
        AddPropertyScreen(rememberNavController())
    }
}