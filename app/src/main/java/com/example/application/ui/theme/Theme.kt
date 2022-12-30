package com.example.application.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import com.greenlabsfin.design.component.util.ChangeStatusBarColor
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.GfColorScheme


@Composable
fun GFSampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = GfColorScheme.default(darkTheme)
    val view = LocalView.current
    if (view.isInEditMode.not()) {
        ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme.not()
        ChangeStatusBarColor(color = colorScheme.container.background)
    }

    GfTheme(
        colorScheme = colorScheme,
        content = content,
        backgroundColor = colorScheme.container.background
    )
}
