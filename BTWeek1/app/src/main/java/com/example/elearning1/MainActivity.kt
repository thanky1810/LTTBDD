package com.example.elearning1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.elearning1.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ProfileApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileApp() {
    AppTheme {
        ProfileScreen()
    }
}
