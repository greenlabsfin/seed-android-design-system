package co.seedglobal.design.component.util

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import co.seedglobal.design.component.SeedSurface
import co.seedglobal.design.core.LocalSeedBackgroundColor

@Composable
fun DecorateBackground(
    color: Color,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalSeedBackgroundColor provides color,
        content = {
            SeedSurface(
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
