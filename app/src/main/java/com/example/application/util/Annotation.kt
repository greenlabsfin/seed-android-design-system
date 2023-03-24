package com.example.application.util

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Dark theme",
    group = "themes",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light theme",
    group = "themes",
    showBackground = true
)
annotation class ThemedPreview
