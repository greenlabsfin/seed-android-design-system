package com.example.application

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.application.ui.home.HomeScreen
import com.example.application.ui.theme.GFSampleTheme
import com.example.application.util.LocaleHelper
import com.greenlabsfin.design.core.LocalGfBackgroundColor

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            GFSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LocalGfBackgroundColor.current
                ) {
//                    SeedMain()
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
