package com.example.application.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.GfColorScheme


@Composable
fun GFSampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = GfColorScheme.default(darkTheme)

    GfTheme(
        colorScheme = colorScheme,
        content = content
    )
}
