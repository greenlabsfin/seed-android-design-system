package com.greenlabsfin.design.core.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class SeedColorScheme(
    val contents: ContentsScheme = ContentsScheme(),
    val container: ContainerScheme = ContainerScheme(),
) {
    data class ContentsScheme(
        val primary: Color = green60,
        val neutralPrimary: Color = gray90,
        val neutralSecondary: Color = gray60,
        val neutralTertiary: Color = gray40,
        val onPrimary: Color = white,
        val onSecondary: Color = green60,
        val onInverse: Color = white,
        val error: Color = red70,
    )

    data class ContainerScheme(
        val primary: Color = green60,
        val secondary: Color = green10,
        val neutralPrimary: Color = white,
        val neutralSecondary: Color = gray20,
        val neutralTertiary: Color = gray10,
        val divider: Color = gray20,
        val outline: Color = gray30,
        val background: Color = white,
        val inverse: Color = gray90,
        val error: Color = red10,
    )

    companion object {
        fun default(
            darkTheme: Boolean,
        ) =
            if (darkTheme) SeedColorScheme(
                contents = ContentsScheme(
                    primary = green60,
                    neutralPrimary = gray10,
                    neutralSecondary = gray40,
                    neutralTertiary = gray60,
                    onPrimary = white,
                    onSecondary = green60,
                    onInverse = gray90,
                    error = red70
                ),
                container = ContainerScheme(
                    primary = green60,
                    secondary = green10A,
                    neutralPrimary = gray95,
                    neutralSecondary = gray10A,
                    neutralTertiary = gray90,
                    divider = gray20,
                    outline = gray30,
                    background = gray95,
                    inverse = gray10,
                    error = red10A
                )
            )
            else SeedColorScheme()
    }
}
