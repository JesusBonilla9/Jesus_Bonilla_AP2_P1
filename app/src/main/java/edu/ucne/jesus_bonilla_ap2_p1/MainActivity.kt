package edu.ucne.jesus_bonilla_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.ucne.jesus_bonilla_ap2_p1.presentation.navigate.RegistroNavHost
import edu.ucne.jesus_bonilla_ap2_p1.ui.theme.Jesus_Bonilla_AP2_P1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jesus_Bonilla_AP2_P1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegistroNavHost(innerPadding = innerPadding)
                }
            }
        }
    }
}


