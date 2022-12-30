package com.greenlabsfin.design.component.util

import android.app.Activity
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import com.greenlabsfin.design.core.LocalGfBackgroundColor

@Composable
fun DecorateBackground(
    color: Color,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalGfBackgroundColor provides color,
        content = {
            Surface(
                color = color,
                content = content,
            )
        }
    )
}

@Composable
fun ChangeStatusBarColor(color: Color) {
    val view = LocalView.current
    if (view.isInEditMode.not()) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = color.toArgb()
        }
    }
}
