package com.addy.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.addy.launcher.ui.theme.AddyTheme
import com.addy.launcher.ui.root.LauncherRoot

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AddyTheme {
                LauncherRoot()
            }
        }
    }
}
