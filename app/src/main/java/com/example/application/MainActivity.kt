package com.example.application

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.application.ui.HomeScreen
import com.example.application.ui.theme.GFSampleTheme
import com.example.application.util.LocaleHelper
import com.greenlabsfin.design.core.GfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GFSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = GfTheme.colorScheme.container.background
                ) {
                    HomeScreen()
                }
            }
        }
    }


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            LocaleHelper.setLocale(newBase, DesignSampleApplication.language)
        )
    }
}
