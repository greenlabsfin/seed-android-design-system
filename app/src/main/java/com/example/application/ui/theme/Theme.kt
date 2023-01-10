package com.example.application.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import co.seedglobal.design.component.util.ChangeStatusBarColor
import co.seedglobal.design.core.SeedTheme
import co.seedglobal.design.core.color.SeedColorScheme


@Composable
fun SeedSampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = SeedColorScheme.default(darkTheme)
    val view = LocalView.current
    if (view.isInEditMode.not()) {
        ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme.not()
        ChangeStatusBarColor(color = colorScheme.container.background)
    }

    SeedTheme(
        colorScheme = colorScheme,
        content = content,
        backgroundColor = colorScheme.container.background
    )
}
