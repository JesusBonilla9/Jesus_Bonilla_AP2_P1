package edu.ucne.jesus_bonilla_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import edu.ucne.jesus_bonilla_ap2_p1.ui.theme.Jesus_Bonilla_AP2_P1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jesus_Bonilla_AP2_P1Theme {
                }
            }
        }
    }


